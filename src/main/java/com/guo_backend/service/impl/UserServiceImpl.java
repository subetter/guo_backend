package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.mapper.UserMapper;
import com.guo_backend.security.JwtHelp;
import com.guo_backend.service.UserService;
import com.guo_backend.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

/**
 * @author fu
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-12-04 23:25:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    UserMapper userMapper;

    private final JwtHelp jwtHelp;

    public UserServiceImpl( UserMapper adminMapper,
                            JwtHelp jwtHelp ) {
        this.userMapper = adminMapper;
        this.jwtHelp = jwtHelp;
    }

    @Override
    public Object login( HttpServletResponse response, String username, String password ) {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        QueryWrapper<User> queryWrapper = Wrappers.<User>query()
                .eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user.getPassword());
        System.out.println(password);
        boolean flag = passwordEncoder.matches(password,user.getPassword());
        System.out.println(flag);
        if (!flag) {
            // 采用异常流处理业务逻辑
            return null;
        }
        else {
            String token = jwtHelp.generateToken(user);
            // 这里不一定是采用cookie，如果是移动端，可以直接返回
            CookiesUtils.setCookies(response, "token", token);
            return this.copy(user);
        }
    }

    @Override
    public UserDto copy( User user ) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .account(user.getAccount())
                .avatar(user.getAvatar())
                .createTime(user.getCreateTime())
                .userId(user.getUserId())
                .userName(user.getUsername())
                .roleId(user.getRoleId())
                .build();
    }
//
//    public User createUser(User user){
//        // 判断数据库中是否存在该用户
//        User newuser = new User();
//        newuser.setUserId(user.getUserId());
////        newuser.setAccount(user.getAccount());
////        newuser.setUserName(user.getUserName());
////        newuser.setRoleId(user.getRoleId());
////        newuser.setAvatar(user.getAvatar());
////        newuser.setPassword(user.getPassword());
////        newuser.setCreateTime(new Date());
//    }

    public BaseResponse register( User user ) {
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUsername()));
        User user2 = userMapper.selectOne(new QueryWrapper<User>().eq("account", user.getAccount()));
        if (user1 == null && user2 == null) {
            User newuser = new User();
            String uuid = UUID.randomUUID().toString();
            newuser.setUserId(uuid);
            newuser.setAccount(user.getAccount());
            newuser.setUserName(user.getUsername());
            newuser.setRoleId(user.getRoleId());
            newuser.setAvatar(user.getAvatar());
            newuser.setPassword(user.getPassword());
            newuser.setCreateTime(new Date());
            int res = userMapper.insert(newuser);
            if (res == 1) {
                return ResultUtils.success(newuser);
            } else {
                return ResultUtils.error(400, "注册失败，请检查网络！");
            }
        } else {
            return ResultUtils.error(500, "用户名或账号已存在,请重新注册!");
        }
    }
}





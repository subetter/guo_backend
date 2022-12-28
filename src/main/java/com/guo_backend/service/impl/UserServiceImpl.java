package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.domain.dto.UserList;
import com.guo_backend.mapper.UserMapper;
import com.guo_backend.security.JwtHelp;
import com.guo_backend.service.UserService;
import com.guo_backend.utils.CookiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public UserServiceImpl(UserMapper adminMapper,
                           JwtHelp jwtHelp) {
        this.userMapper = adminMapper;
        this.jwtHelp = jwtHelp;
    }

    @Override
    public Object login(HttpServletResponse response, String username, String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        QueryWrapper<User> queryWrapper = Wrappers.<User>query()
                .eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user.getPassword());
        System.out.println(password);
        boolean flag = passwordEncoder.matches(password, user.getPassword());
        System.out.println(flag);
        if (!flag) {
            // 采用异常流处理业务逻辑
            return null;
        } else {
            String token = jwtHelp.generateToken(user);
            // 这里不一定是采用cookie，如果是移动端，可以直接返回
            CookiesUtils.setCookies(response, "token", token);
            return this.copy(user);
        }
    }

    @Override
    public UserDto copy(User user) {
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


    @Override
    public Object register(User user) {
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
            // 密码加密存储在数据库中
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            newuser.setPassword(passwordEncoder.encode(user.getPassword()));
            newuser.setCreateTime(new Date());
            int res = userMapper.insert(newuser);
            if (res == 1) {
                return this.copy(newuser);
            }
        }
        return null;
    }

    @Override
    public UserList getalluser() {
//        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        //存储最终结果
        List<User> list=userMapper.selectList(null);

        return UserList.builder()
                .result(list)
                .build();
    }

    @Override
    public Boolean upUserRoleId(User user) {
        boolean flag;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user.getUserId());
        User user1=userMapper.selectOne(queryWrapper);
        user1.setRoleId(user.getRoleId());
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("role_id",user1.getRoleId());
        int res=userMapper.update(user1,queryWrapper);
        if(res>0){
            flag=true;
        }
        else
            flag=false;
        return flag;
    }
}





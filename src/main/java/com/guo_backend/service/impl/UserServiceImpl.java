package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.mapper.UserMapper;
import com.guo_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public BaseResponse register(User user) {
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", user.getUserName()));
        User user2 = userMapper.selectOne(new QueryWrapper<User>().eq("account", user.getAccount()));
        if (user1 == null && user2 == null) {
            User newuser = new User();
            String uuid = UUID.randomUUID().toString();
            newuser.setUserId(uuid);
            newuser.setAccount(user.getAccount());
            newuser.setUserName(user.getUserName());
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





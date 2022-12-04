package com.guo_backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guo_backend.domain.User;
import com.guo_backend.mapper.UserMapper;
import com.guo_backend.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author fu
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-12-04 23:25:27
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}





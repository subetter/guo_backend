package com.guo_backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;

import javax.servlet.http.HttpServletResponse;

/**
 * @author fu
 * @description 针对表【user】的数据库操作Service
 * @createDate 2022-12-04 23:25:27
 */
public interface UserService extends IService<User> {
    Object login( HttpServletResponse response, String username, String password);
    UserDto copy( User User );
}

package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author happy
 */
@RestController
@RequestMapping(value = "/api/auth")
@Component
@Tag(name = "登录、注册、获取个人信息")
public class AuthController {
    @Resource
    private UserService userService;

    public AuthController( UserService adminService ) {
        this.userService = adminService;
    }

    @PostMapping(value = "login")
    public BaseResponse<Object> login( @RequestParam String username, @RequestParam String password, HttpServletResponse response ) {
        Object userDto = userService.login(response, username, password);
        if (userDto == null) {
            return ResultUtils.error(200, "用户名或密码错误");
        }
        return ResultUtils.success(userDto);
    }

}

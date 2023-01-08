package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.CourseinfoDto;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.service.CourseInfoService;
import com.guo_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
@Tag(name = "登录、注册")
@CrossOrigin
public class AuthController {
    @Resource
    private UserService userService;


    @PostMapping(value = "/login")
    @Operation(summary = "登录接口")
    public BaseResponse<Object> login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        Object userDto = userService.login(response, username, password);
        if (userDto == null) {
            return ResultUtils.error(200, "用户名或密码错误");
        }
        return ResultUtils.success(userDto);
    }

    @Operation(summary = "注册接口")
    @PostMapping(value = "/register")
    public BaseResponse<Object> register(User user) {
        Object userDto = userService.register(user);
        if (userDto == null) {
            return ResultUtils.error(500, "用户名或账号已存在，请重新注册！");
        } else {
            return ResultUtils.success(userDto);
        }
    }


}

package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author fu
 * @Date 2022 12 23 11 27
 **/
@Component
@RestController
@RequestMapping("user")
@Slf4j
@Tag(name = "用户")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/userinfo")
    public BaseResponse<Object> currentUser(@AuthenticationPrincipal User user) {
        UserDto userDto = userService.copy(user);
        return ResultUtils.success(userDto);
    }


}

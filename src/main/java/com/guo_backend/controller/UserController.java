package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.common.ResultUtils;
import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.domain.dto.UserList;
import com.guo_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author fu
 * @Date 2022 12 23 11 27
 **/
@Component
@RestController
//@RequestMapping("user")
@Slf4j
@Tag(name = "用户")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping(value = "/user/userinfo")
    public BaseResponse<Object> currentUser(@AuthenticationPrincipal User user) {
        UserDto userDto = userService.copy(user);
        return ResultUtils.success(userDto);
    }
    @Operation(summary = "查询所有用户")
    @GetMapping(value = "/get/alluser")
    public BaseResponse<UserList> getalluser(){
        return ResultUtils.success(userService.getalluser());
    }


    @Operation(summary = "修改用户角色id")
    @PostMapping(value = "/update/roleid")
   public BaseResponse<Boolean> upUserRoleId( User user){
        return ResultUtils.success(userService.upUserRoleId(user));
   }
}

package com.guo_backend.controller;

import com.guo_backend.common.BaseResponse;
import com.guo_backend.domain.User;
import com.guo_backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yasu
 */
@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    UserServiceImpl usvc;

    @PostMapping(value = "/register")
    public BaseResponse register(@RequestBody User user) {
        return usvc.register(user);
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

}

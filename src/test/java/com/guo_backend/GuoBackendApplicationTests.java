package com.guo_backend;

import com.guo_backend.domain.User;
import com.guo_backend.domain.dto.UserDto;
import com.guo_backend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class GuoBackendApplicationTests {
    @Resource
    private UserService userService;
    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches("123456","$2a$10$6N0NQ8nmAYY1.vLYPI/wB.GF8VumL3VX8ure9q4YBsD322HPtXydy"));
    }
}

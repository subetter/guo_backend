package com.guo_backend;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFileStorage
@MapperScan("com.guo_backend.mapper")
public class GuoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuoBackendApplication.class, args);
    }

}

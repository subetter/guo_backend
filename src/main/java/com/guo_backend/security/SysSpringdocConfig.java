package com.guo_backend.security;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fu
 */
@Configuration
public class SysSpringdocConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("软件设计与体系结构").version("1.0")
                .description("Knife4j集成springdoc-openapi示例")
                .termsOfService("http://doc.xiaominfo.com")
                .license(new License().name("Apache 2.0").url("http://doc.xiaominfo.com")));
    }

    @Bean
    public GroupedOpenApi menuApi() {
        return GroupedOpenApi.builder()
                .group("订单系统")
                .packagesToScan("com.guo_backend.controller")
                .build();

    }

}

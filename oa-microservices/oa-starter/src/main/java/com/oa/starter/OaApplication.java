package com.oa.starter;

import com.oa.starter.config.*;
import com.oa.starter.controller.*;
import com.oa.starter.mapper.*;
import com.oa.starter.service.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.oa.common", "com.oa.starter"})
@MapperScan("com.oa.starter.mapper")
public class OaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaApplication.class, args);
    }
}

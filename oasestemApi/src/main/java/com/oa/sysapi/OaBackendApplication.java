package com.oa.sysapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.oa.sysapi.mapper")
public class OaBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OaBackendApplication.class, args);
    }
}

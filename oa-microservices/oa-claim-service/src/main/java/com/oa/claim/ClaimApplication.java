package com.oa.claim;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.oa.common", "com.oa.claim"})
@MapperScan("com.oa.claim.mapper")
public class ClaimApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClaimApplication.class, args);
    }
}

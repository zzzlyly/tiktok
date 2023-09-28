package com.cdut.tiktok.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.cdut.tiktok.user.dao")
@SpringBootApplication
public class TiktokUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokUserApplication.class, args);
    }

}

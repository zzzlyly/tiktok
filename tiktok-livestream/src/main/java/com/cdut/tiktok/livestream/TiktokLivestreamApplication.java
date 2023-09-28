package com.cdut.tiktok.livestream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.cdut.tiktok.livestream.dao")
@SpringBootApplication
public class TiktokLivestreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokLivestreamApplication.class, args);
    }

}

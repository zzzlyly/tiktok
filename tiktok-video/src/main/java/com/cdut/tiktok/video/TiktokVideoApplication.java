package com.cdut.tiktok.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.cdut.tiktok.video.dao")
@SpringBootApplication
public class TiktokVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokVideoApplication.class, args);
    }

}

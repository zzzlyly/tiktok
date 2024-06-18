package com.cdut.tiktok.video;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.cdut.tiktok.video.feign")
@EnableDiscoveryClient
@MapperScan("com.cdut.tiktok.video.dao")
@SpringBootApplication
@ComponentScan(basePackages = {"com.cdut.tiktok.video", "com.cdut.tiktok.common"})
public class TiktokVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokVideoApplication.class, args);
    }

}

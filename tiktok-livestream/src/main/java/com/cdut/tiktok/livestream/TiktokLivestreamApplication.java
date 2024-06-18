package com.cdut.tiktok.livestream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.cdut.tiktok.livestream.dao")
@SpringBootApplication
@ComponentScan(basePackages = {"com.cdut.tiktok.livestream", "com.cdut.tiktok.common"})
public class TiktokLivestreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokLivestreamApplication.class, args);
    }

}

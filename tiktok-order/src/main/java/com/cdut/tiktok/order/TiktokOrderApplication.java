package com.cdut.tiktok.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.cdut.tiktok.order.dao")
@EnableDiscoveryClient
@SpringBootApplication
public class TiktokOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokOrderApplication.class, args);
    }

}

package com.cdut.tiktok.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@MapperScan("com.cdut.tiktok.product.dao")
@SpringBootApplication
public class TiktokProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokProductApplication.class, args);
    }

}

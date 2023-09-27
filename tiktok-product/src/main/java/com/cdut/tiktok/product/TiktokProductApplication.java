package com.cdut.tiktok.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.cdut.tiktok.product.dao")
@SpringBootApplication
public class TiktokProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiktokProductApplication.class, args);
    }

}

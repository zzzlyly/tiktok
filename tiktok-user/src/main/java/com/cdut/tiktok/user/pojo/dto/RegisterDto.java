package com.cdut.tiktok.user.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterDto {

    private String name;

    private String password;

    private String code;

    private String email ;

    private String key ;

    /**
     * 性别 0-未知 1-male,2-female
     */
    private Integer gender;
    /**
     * 个人简介
     */
    private String signature;
    /**
     * ip归属地
     */
    private String iplocation;
    /**
     * 出生日期
     */
    private LocalDate dateOfBirth;
    /**
     * 毕业院校
     */
    private String almaMater;
    /**
     * 当前所在地
     */
    private String currentResidence;


}

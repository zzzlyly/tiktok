package com.cdut.tiktok.auth.pojo.dto;

import lombok.Data;

@Data
public class AuthUserDto {

    private String userName;

    private String password;

    private String code;

    private String email ;

    private String key ;

}

package com.cdut.tiktok.auth.service;

import com.cdut.tiktok.auth.entity.UserEntity;
import com.cdut.tiktok.auth.pojo.dto.AuthUserDto;
import com.cdut.tiktok.auth.pojo.vo.AuthCaptchaVo;


public interface AuthService {
    /**
     * 发送邮件
     *
     */
    void sendMailCode(String authEmail);

    void register(AuthUserDto authUserDto);

    AuthCaptchaVo getCaptcha();

    UserEntity getById(String userId);
}

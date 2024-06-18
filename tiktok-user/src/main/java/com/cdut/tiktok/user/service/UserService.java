package com.cdut.tiktok.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.user.entity.UserEntity;
import com.cdut.tiktok.user.pojo.dto.LoginUserDto;
import com.cdut.tiktok.user.pojo.dto.RegisterDto;
import com.cdut.tiktok.user.pojo.to.LiveGetUserEntityTo;
import com.cdut.tiktok.user.pojo.vo.AuthCaptchaVo;
import com.cdut.tiktok.user.pojo.vo.UserEntityVo;

import java.util.Map;

/**
 * 
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);


    boolean registerUser(RegisterDto user);

    AuthCaptchaVo getCaptcha();

    String login(LoginUserDto user);

    UserEntity getUserByName(String name);

    UserEntityVo getUserForLive(LiveGetUserEntityTo liveGetUserEntityTo);

}


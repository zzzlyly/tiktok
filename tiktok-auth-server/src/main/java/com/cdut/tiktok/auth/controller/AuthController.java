package com.cdut.tiktok.auth.controller;


import com.cdut.tiktok.auth.pojo.dto.AuthEmailDto;
import com.cdut.tiktok.auth.pojo.dto.AuthUserDto;
import com.cdut.tiktok.auth.pojo.vo.AuthCaptchaVo;
import com.cdut.tiktok.auth.service.AuthService;
import com.cdut.tiktok.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 *
 *
 * @author zhanglingyun
 * @email zhanglingyunn@foxmail.com
 * @date 2023-09-27 19:27:51
 */
@RestController
@RequestMapping("auth/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 发送邮箱验证码
     * @param
     * @return
     */
    @PostMapping(value = "/getEmailCode")
    public R getEmailCode(@RequestBody AuthEmailDto authEmailDto) {
        authService.sendMailCode(authEmailDto.getAuthEmail());
        return R.ok();
    }

    /**
     * 获取谷歌验证码
     * @param
     * @return
     */
    @GetMapping("/captcha")
    public R getCaptcha(){
        AuthCaptchaVo authCaptchaVo = authService.getCaptcha();
        return R.ok().put("key",authCaptchaVo.getKey()).put("captcha",authCaptchaVo.getCaptcherImg());
    }



    /**
     * 注册
     * @param authUserDto
     * @return
     */
    @PostMapping("/register")
    public R register(@RequestBody AuthUserDto authUserDto){
        authService.register(authUserDto);
        return R.ok();
    }

}

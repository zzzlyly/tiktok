package com.cdut.tiktok.auth.service.impl;


import cn.hutool.extra.mail.MailAccount;
import com.cdut.tiktok.auth.entity.UserEntity;
import com.cdut.tiktok.auth.feign.UserFeignService;
import com.cdut.tiktok.auth.pojo.dto.AuthUserDto;
import com.cdut.tiktok.auth.pojo.vo.AuthCaptchaVo;
import com.cdut.tiktok.auth.service.AuthService;
import com.cdut.tiktok.auth.utils.CodeGenerator;
import com.cdut.tiktok.common.utils.R;
import com.google.code.kaptcha.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthServiceImpl implements AuthService {
    @Value("${spring.mail.email}")
    private String email;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    Producer producer;

    @Autowired
    UserFeignService userFeignService;



    @Override
    public void sendMailCode(String authEmail) {
        // 读取邮箱配置
        if (email == null || host == null || port == null || username == null || password == null) {
            throw new RuntimeException("邮箱配置异常");
        }

        // 设置
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(Integer.parseInt(port));
        // 设置发送人邮箱
        account.setFrom(email);
        // 设置发送人名称
        account.setUser(username);
        // 设置发送授权码
        account.setPass(password);
        account.setAuth(true);
        // ssl方式发送
        account.setSslEnable(true);
        // 使用安全连接
        account.setStarttlsEnable(true);


        // 发送邮件
        try {
            CodeGenerator codeGenerator = new CodeGenerator();
            String code = codeGenerator.getCode(6);
            //Mail.create(account)
            //        .setTos(authEmail)
            //        .setTitle(EmailConstant.EMAIL_TITLE)
            //        .setContent(EmailConstant.EMAIL_CONTENT_HEAD + code + EmailConstant.EMAIL_CONTENT_END)
            //        .setHtml(true)
            //        //关闭session
            //        .setUseGlobalSession(false)
            //        .send();

            // 设置存储的键和过期时间，比如以用户ID为键
            String key = "email::" + authEmail;
            // 过期时间为10分钟
            long expirationInSeconds = 600;
            ValueOperations<String, String> ops = redisTemplate.opsForValue();
            ops.set(key,code);

            // 设置键的过期时间
            //redisTemplate.expire(key, expirationInSeconds, TimeUnit.SECONDS);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void register(AuthUserDto authUserDto) {

    }

    @Override
    public AuthCaptchaVo getCaptcha(){
        String key = UUID.randomUUID().toString();
        String code = producer.createText();

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";

        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 构造一个包含key的hash键
        String hashKey = "captcha:" + key;
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        // 使用哈希存储验证码
        hashOps.put(hashKey, "code", code);
        // 设置整个哈希键的过期时间
        redisTemplate.expire(hashKey, 120, TimeUnit.SECONDS);
        AuthCaptchaVo authCaptchaVo = new AuthCaptchaVo();
        authCaptchaVo.setCaptcherImg(base64Img);
        authCaptchaVo.setKey(key);
        return authCaptchaVo;
    }

    @Override
    public UserEntity getById(String userId) {
        R result = userFeignService.getUserInfo(userId);
        return (UserEntity)result.get("user");
    }
}

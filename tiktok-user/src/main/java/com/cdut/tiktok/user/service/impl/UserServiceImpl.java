package com.cdut.tiktok.user.service.impl;

import com.cdut.tiktok.common.utils.JwtUtils;
import com.cdut.tiktok.user.pojo.dto.LoginUserDto;
import com.cdut.tiktok.user.pojo.dto.RegisterDto;
import com.cdut.tiktok.user.pojo.to.LiveGetUserEntityTo;
import com.cdut.tiktok.user.pojo.vo.AuthCaptchaVo;
import com.cdut.tiktok.user.pojo.vo.UserEntityVo;
import com.google.code.kaptcha.Producer;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cdut.tiktok.common.utils.PageUtils;
import com.cdut.tiktok.common.utils.Query;

import com.cdut.tiktok.user.dao.UserDao;
import com.cdut.tiktok.user.entity.UserEntity;
import com.cdut.tiktok.user.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private Producer producer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean registerUser(RegisterDto user) {
        // 检查验证码是否正确
        String hashKey = "captcha:" + user.getKey();
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        String storedCode = (String) hashOps.get(hashKey, "code");

        if (!user.getCode().equals(storedCode)) {
            return false;  // 验证码不正确，直接返回失败
        }

        // 检查邮箱是否已被注册
        UserEntity existingUser = userDao.selectOne(new QueryWrapper<UserEntity>().eq("email", user.getEmail()));
        if (existingUser != null) {
            return false;  // 邮箱已被注册，返回失败
        }

        UserEntity registerUser = new UserEntity();
        // 使用Spring BeanUtils复制属性
        BeanUtils.copyProperties(user, registerUser);
        // 加密密码
        registerUser.setPassword(passwordEncoder.encode(user.getPassword()));

        // 插入用户数据
        boolean inserted = userDao.insert(registerUser) > 0;
        if (inserted) {
            // 注册成功后删除Redis中的验证码
            redisTemplate.delete(hashKey);
        }

        return inserted;
    }


    @Override
    public AuthCaptchaVo getCaptcha() {
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
    public String login(LoginUserDto user) {
        // 检查验证码是否正确
        String hashKey = "captcha:" + user.getKey();
        HashOperations<String, Object, Object> hashOps = redisTemplate.opsForHash();
        String storedCode = (String) hashOps.get(hashKey, "code");

        if (!user.getCode().equals(storedCode)) {
            return null;  // 验证码不正确，直接返回失败
        }

        // 用户名和密码验证
        UserEntity userEntity = getUserByName(user.getName());
        if (userEntity == null || !passwordEncoder.matches(user.getPassword(), userEntity.getPassword())) {
            return null;  // 用户名不存在或密码错误
        }

        // 认证成功，生成Token
        return jwtUtils.generateToken(userEntity.getId().toString());
    }


    @Override
    public UserEntity getUserByName(String name) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);  // 构建条件：字段email等于方法参数email
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public UserEntityVo getUserForLive(LiveGetUserEntityTo liveGetUserEntityTo) {
        UserEntity userEntity = userDao.selectById(liveGetUserEntityTo.getId());
        UserEntityVo userEntityVo = new UserEntityVo();

        BeanUtils.copyProperties(userEntity, userEntityVo);

        if (liveGetUserEntityTo.getGiftAmount() != null) {
            // compareTo: <0 means less, 0 means equal, >0 means greater
            int comparison = liveGetUserEntityTo.getGiftAmount().compareTo(userEntity.getCoins());
            if (comparison <= 0) {  // 用户余额足够
                // 扣除金额
                userEntity.setCoins(userEntity.getCoins().subtract(liveGetUserEntityTo.getGiftAmount()));
                userDao.updateById(userEntity);
            } else {  // 余额不足
                userEntityVo.setName("");
            }
        }
        return userEntityVo;
    }



}
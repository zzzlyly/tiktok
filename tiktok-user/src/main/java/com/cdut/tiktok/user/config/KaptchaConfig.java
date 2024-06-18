package com.cdut.tiktok.user.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    /**
     * Kaptcha图形验证码工具配置类
     * @author: zzzlyly
     * @param:
     * @return: com.google.code.kaptcha.Producer
     * @date: 2022/9/9 15:47
     */
    @Bean
    public Producer kaptchaProducer() {
        // 实例一个DefaultKaptcha
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        // 创建配置对象
        Properties properties = new Properties();
        // 设置边框
        properties.setProperty("kaptcha.border", "yes");
        // 设置颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        // 设置字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        // 设置宽度
        properties.setProperty("kaptcha.image.width", "125");
        // 高度
        properties.setProperty("kaptcha.image.height", "50");
        // 设置session.key
        properties.setProperty("kaptcha.session.key", "code");
        // 设置文本长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        // 设置字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        // 将以上属性设置为实例一个DefaultKaptcha的属性
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        // 将defaultKaptcha返回
        return defaultKaptcha;
    }
}
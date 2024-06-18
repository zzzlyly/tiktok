package com.cdut.tiktok.auth.utils;

import java.util.Random;

public class CodeGenerator {

    public String getCode(Integer codeLength){
        // 生成随机数
        Random random = new Random();

        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < codeLength; i++ ) {
            // 生成 0 到 9 之间的随机数
            int digit = random.nextInt(10);
            verificationCode.append(digit);
        }
        return verificationCode.toString();
    }
}

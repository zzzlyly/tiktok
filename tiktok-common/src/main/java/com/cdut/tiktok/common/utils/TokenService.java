package com.cdut.tiktok.common.utils;

import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private JwtUtils jwtUtils;

    public Long getUserIdFromToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new MyException(ExceptionCode.INVALID_ARGUMENT);
        }
        return jwtUtils.getUserIdFromToken(token);
    }
}

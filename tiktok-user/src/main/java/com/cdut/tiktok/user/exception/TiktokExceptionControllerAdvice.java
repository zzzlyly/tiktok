package com.cdut.tiktok.user.exception;

import com.cdut.tiktok.common.exception.ExceptionCode;
import com.cdut.tiktok.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 集中处理所有异常
 */
@Slf4j
@RestControllerAdvice(basePackages = {"com.cdut.tiktok.user.controller", "com.cdut.tiktok.user.service.impl"})
public class TiktokExceptionControllerAdvice {

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("发生错误{},异常类型为{}",throwable.getMessage(),throwable.getClass());
        return R.error(ExceptionCode.CUSTOM_ERROR.getCode(), throwable.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public R handleRuntimeException(RuntimeException ex) {
        log.error("发生错误{}, 异常类型为{}", ex.getMessage(), ex.getClass());
        return R.error(ExceptionCode.CUSTOM_ERROR.getCode(), ex.getMessage());
    }
}

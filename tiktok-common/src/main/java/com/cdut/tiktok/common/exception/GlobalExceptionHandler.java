package com.cdut.tiktok.common.exception;


import com.alibaba.fastjson.JSON;
import com.cdut.tiktok.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author wangjiao
 * @since 2020/11/14
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 非法参数验证异常
     *
     * @param ex ex
     * @return res
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public R handleMethodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException ex) {
        log.error("fieldErrors:[ex:{}]", JSON.toJSONString(ex.getMessage()));
        return R.error(ExceptionCode.INVALID_ARGUMENT.getCode(), ExceptionCode.INVALID_ARGUMENT.getMessage());
    }

    /**
     * 系统登录异常处理
     *
     * @param exception exception
     * @return res
     */
    @ExceptionHandler(value = SysLoginException.class)
    @ResponseStatus(HttpStatus.OK)
    public R sysLoginExceptionHandler(SysLoginException exception) {
        log.warn("sysLoginExceptionHandler:系统登录异常[exception:{}]", exception.getMessage());
        return R.error(exception.getCode(), exception.getMessage());
    }

    /**
     * HTTP解析请求参数异常
     *
     * @param e e
     * @return res
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public R httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("httpMessageNotReadableException:[e:{}]", e.getMessage());
        return R.error(ExceptionCode.INVALID_ARGUMENT.getCode(), ExceptionCode.INVALID_ARGUMENT.getMessage());
    }

    /**
     * 自定义业务/数据异常处理
     *
     * @param exception exception
     * @return res
     */
    @ExceptionHandler(value = {MyException.class})
    @ResponseStatus(HttpStatus.OK)
    public R springBootPlusExceptionHandler(MyException exception) {
        log.error("MyException:[exception:{}]", exception.getMessage());
        return R.error(exception.getCode(), exception.getMessage());
    }


    /**
     * 未认证异常处理
     *
     * @param exception exception
     * @return res
     */
    @ExceptionHandler(value = UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.OK)
    public R unauthenticatedExceptionHandler(UnauthenticatedException exception) {
        log.error("unauthenticatedExceptionHandler:[exception:{}]", exception.getMessage());
        return R.error(ExceptionCode.UNAUTHENTICATED.getCode(),ExceptionCode.UNAUTHENTICATED.getMessage());
    }


    /**
     * SQL 语法异常
     *
     * @param exception exception
     * @return res
     */
    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseStatus(HttpStatus.OK)
    public R badSqlGrammarException(BadSqlGrammarException exception) {
        log.info("badSqlGrammarException:[exception:{}]", exception.getMessage());
        return R.error(ExceptionCode.INTERNAL_SERVER_ERROR.getCode(),ExceptionCode.INTERNAL_SERVER_ERROR.getMessage());
    }
    /**
     * 默认的异常处理
     *
     * @param exception exception
     * @return res
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public R exceptionHandler(Exception exception) {
        log.error("exceptionHandler:[exception:{}]", exception.getMessage());
        return R.error(ExceptionCode.UNKNOWN_ERROR.getCode(),ExceptionCode.UNKNOWN_ERROR.getMessage());
    }
}


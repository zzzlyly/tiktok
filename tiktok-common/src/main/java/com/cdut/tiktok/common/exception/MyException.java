package com.cdut.tiktok.common.exception;

/**
 * @author zzzlyly
 */
public class MyException extends RuntimeException{
    private Integer code;

    public MyException(ExceptionCode exceptionCode){
        super(exceptionCode.getMessage());
        this.code = exceptionCode.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

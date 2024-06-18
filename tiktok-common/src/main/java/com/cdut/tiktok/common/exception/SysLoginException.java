package com.cdut.tiktok.common.exception;

public class SysLoginException extends RuntimeException{
    private Integer code;

    public SysLoginException(){
        super(ExceptionCode.LOGIN_FAILED.getMessage());
        this.code = ExceptionCode.LOGIN_FAILED.getCode();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return ExceptionCode.LOGIN_FAILED.getMessage();
    }

}

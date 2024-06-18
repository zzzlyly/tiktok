package com.cdut.tiktok.common.exception;

public enum ExceptionCode {
    // 通用错误
    UNKNOWN_ERROR(500, "未知错误"),
    // 参数错误
    INVALID_ARGUMENT(400, "参数错误"),
    // 权限错误
    UNAUTHENTICATED(401, "未认证"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源未找到"),
    LOGIN_FAILED(405, "资源未找到"),
    // 业务错误
    ALREADY_EXISTS(409, "资源已存在"),
    FAILED_PRECONDITION(412, "失败的前提条件"),
    FILESTORAGE_ERROR(413, "OSS存储服务异常"),
    // 服务器错误
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    SERVICE_UNAVAILABLE(503, "服务不可用"),
    // 自定义错误
    CUSTOM_ERROR(600, "自定义错误");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

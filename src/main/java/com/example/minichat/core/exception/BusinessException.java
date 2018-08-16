package com.example.minichat.core.exception;


/**
 * @Author by guanda
 * @Date 2018/7/30 11:37
 */

public class BusinessException extends BaseException {
    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable e) {
        super(message, e);
    }
}

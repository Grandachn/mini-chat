package com.example.minichat.core.exception;

/**
 * @Author by guanda
 * @Date 2018/7/30 11:19
 */

public class BaseException extends Exception{

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable e) {
        super(message, e);
    }
}

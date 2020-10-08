package com.wuhanyunzhong.itass.service.exception;

public class DggException extends ServiceException{
    public DggException() {
    }

    public DggException(String message) {
        super(message);
    }

    public DggException(String message, Throwable cause) {
        super(message, cause);
    }

    public DggException(Throwable cause) {
        super(cause);
    }

    public DggException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package com.wuhanyunzhong.itass.service.exception;

public class serviceException  extends  RuntimeException{
    public serviceException() {
        super();
    }

    public serviceException(String message) {
        super(message);
    }

    public serviceException(String message, Throwable cause) {
        super(message, cause);
    }

    public serviceException(Throwable cause) {
        super(cause);
    }

    protected serviceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

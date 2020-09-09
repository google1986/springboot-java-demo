package com.htzw.exceptionhandler.exception;

/**
 * 自定义异常类
 */
public class NotFountResourceException extends RuntimeException{

    private static final long serialVersionUID = 5475644442223562970L;

    public NotFountResourceException() {
        super();
    }

    public NotFountResourceException(String message) {
        super(message);
    }

    public NotFountResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFountResourceException(Throwable cause) {
        super(cause);
    }

}

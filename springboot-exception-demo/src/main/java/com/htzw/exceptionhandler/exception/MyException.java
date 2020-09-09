package com.htzw.exceptionhandler.exception;

/**
 * 自定义异常类
 *
 * 在平时很多时候Java自带的异常类，就不足以处理我们的业务逻辑
 */
public class MyException extends RuntimeException{

    private static final long serialVersionUID = 594419099921959195L;

    public MyException() {
        super();
    }

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyException(Throwable cause) {
        super(cause);
    }

}

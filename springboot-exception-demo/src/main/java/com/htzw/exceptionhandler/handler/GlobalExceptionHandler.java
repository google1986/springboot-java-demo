package com.htzw.exceptionhandler.handler;

import com.htzw.exceptionhandler.enums.ResultEnum;
import com.htzw.exceptionhandler.entity.ResponseInfo;
import com.htzw.exceptionhandler.exception.MyException;
import com.htzw.exceptionhandler.exception.NotFountResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 *
 * @RestControllerAdvice代替@ControllerAdvice,这样在方法上就可以不需要添加@ResponseBody
 *
 *
 * 写一个异常处理器类加上 @RestControllerAdvice 注解，它的功能和 @ControllerAdvice 注解几乎一致，
 * 都是用于捕获异常信息，唯一的区别就是在该类中的所有方法不需要再单独添加 @ResponseBody 注解来标识
 * 将方法返回的信息转换成 JSON 字符串。
 *
 * 在类中创建几个方法并在方法上单独加上 @ExceptionHandler 注解，这样来表示该方法是用于处理指定异常的，
 * 当有异常信息从 Controller 层抛出被捕获后，将会根据异常类的不同而到不同的异常处理器中进行异常处理。
 *
 * 在异常发生且没有在方法中捕获往上级抛出时，Spring 会在此类中寻找有没有专门处理该抛出的异常的处理器，
 * 如果存在就交由此异常处理器处理，如果不存该异常的异常处理器，那么会检查是否存在指定处理 Exception
 * 异常的异常处理器，因为 Exception 是除 Error 外的全部异常类的父类，所以它能处理在 Spring 中调用
 * Controller 层进而导致异常并抛出的全部异常信息
 *
 * @author Administrator
 */
@RestControllerAdvice(basePackages = "com.htzw.exceptionhandler.controller")
public class GlobalExceptionHandler {

    /**
     * 全局异常处理器
     *
     * @param e Exception
     * @return ResponseInfo
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseInfo globalExceptionHandler(Exception e, HttpServletResponse response) {
        // 判断是否为 MyException 异常
        if (e instanceof MyException) {
            // 设置 HTTP 状态码
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            // 返回错误信息
            return new ResponseInfo()
                    .setMessage(e.getMessage())
                    .setCode(ResultEnum.PARAMETER_ERROR.getCode());
        }
        // 判断是否为 NotFountResourceException 异常
        else if (e instanceof NotFountResourceException) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.NOT_FOUNT_RESOURCE.getCode())
                    .setMessage(e.getMessage());
        }
        // 判断是否为丢失请求参数异常
        else if (e instanceof MissingServletRequestParameterException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.PARAMETER_MISSING_ERROR.getCode())
                    .setMessage(ResultEnum.PARAMETER_MISSING_ERROR.getMessage());
        }
        // 判断是否为缺少请求体异常
        else if (e instanceof HttpMessageNotReadableException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.REQUEST_MISSING_BODY_ERROR.getCode())
                    .setMessage(ResultEnum.REQUEST_MISSING_BODY_ERROR.getMessage());
        }
        // 判断是否为请求参数错误异常
        else if (e instanceof MethodArgumentNotValidException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseInfo()
                    .setCode(ResultEnum.PARAMETER_ERROR.getCode())
                    .setMessage(ResultEnum.PARAMETER_ERROR.getMessage());
        }
        // 不知道异常原因，默认返回未知异常
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseInfo()
                .setCode(ResultEnum.UNKNOWN_ERROR.getCode())
                .setMessage(ResultEnum.UNKNOWN_ERROR.getMessage());
    }

}
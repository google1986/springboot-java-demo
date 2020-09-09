package com.htzw.exceptionhandler.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *自定义返回实体类
 *
 *
 * 提前创建对接口调用放响应的实体类，在成功调用接口或者发生异常时，都应通过该响应实体类进行反馈信息，
 * 在此类中设置三个属性，分别为 code（返回码）、message（错误信息）和 result（返回结果）。
 *
 * 如果发生异常就将异常返回码和异常信息存入 code 和 message 属性中，进行响应。
 * 如果调用成功则将成功对应的返回码与返回消息存入 code 和 message 属性中，
 * 之后将“响应数据”存入 Object 类型的 result 属性中，进行响应。
 *
 * @author Administrator
 */
@Data
@Accessors(chain = true)
public class ResponseInfo {

    /** 错误码 */
    private Integer code;
    /** 错误信息 */
    private String message = "";
    /** 返回结果 */
    private Object data = "";

}

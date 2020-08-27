package com.htzw.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 并使用 @JSONField 注解，以便实现自定义转换：
 *
 * format 参数用于格式化 date 属性。
 * 默认情况下， FastJson 库可以序列化 Java bean 实体， 但我们可以使用 serialize 指定字段不序列化。
 * 使用 ordinal 参数指定字段的顺序
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/27 10:06
 */
@Data
public class Person2 {
    @JSONField(name="AGE")
    private int age;

    @JSONField(name="LAST NAME", ordinal = 2)
    private String lastName;

    @JSONField(name="FIRST NAME", ordinal = 1)
    private String firstName;

    @JSONField(name="DATE OF BIRTH", format="yyyy-MM-dd HH:mm:ss.SSS", ordinal = 3, deserializeUsing =DateExtraProcessor.class )
    private Date dateOfBirth;

    public Person2(int age, String lastName, String firstName, Date dateOfBirth) {
        super();
        this.age = age;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
    }
}

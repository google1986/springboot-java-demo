package com.htzw.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * @JSONField 的作用对象:
 *
 * 1. Field
 * 2. Setter 和 Getter 方法
 * 注意：FastJson 在进行操作时，是根据 getter 和 setter 的方法进行的，并不是依据 Field 进行。
 *
 * 注意：若属性是私有的，必须有 set 方法。否则无法反序列化。
 *
 * public @interface JSONField {
 *     // 配置序列化和反序列化的顺序，1.1.42版本之后才支持
 *     int ordinal() default 0;
 *
 *      // 指定字段的名称
 *     String name() default "";
 *
 *     // 指定字段的格式，对日期格式有用
 *     String format() default "";
 *
 *     // 是否序列化
 *     boolean serialize() default true;
 *
 *     // 是否反序列化
 *     boolean deserialize() default true;
 * }
 *
 *
 * @author gu.lian.jun
 * @version 1.0
 * @date 2020/8/27 9:59
 */
@Data
public class Person {

    @JSONField(name = "AGE")
    private int age;

    @JSONField(name = "FULL NAME", ordinal = 1)
    private String fullName;

    @JSONField(name = "DATE OF BIRTH", ordinal = 2)
    private Date dateOfBirth;

    public Person(int age, String fullName, Date dateOfBirth) {
        super();
        this.age = age;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }
}

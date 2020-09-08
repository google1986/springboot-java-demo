package com.htzw.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @author J.L.G
 * @version 1.0
 * @date 2020/9/7 15:43
 */
@Data
@Accessors(chain = true)
public class User {
    @MongoId
    private String id;
    private String name;
    private String sex;
    private Integer salary;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
    private String remark;
    private Status status;
}

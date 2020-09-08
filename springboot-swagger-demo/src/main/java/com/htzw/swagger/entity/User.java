package com.htzw.swagger.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author J.L.G
 * @version 1.0
 * @date 2020/9/8 9:22
 */
@Data
public class User {
    @ApiModelProperty(value = "姓名", required = true)
    private String name;
    @ApiModelProperty(value = "性别", required = true)
    private String sex;
    @ApiModelProperty(value = "岁数", required = true)
    private Integer age;
    @ApiModelProperty(value = "生日")
    private Date birthday;
}

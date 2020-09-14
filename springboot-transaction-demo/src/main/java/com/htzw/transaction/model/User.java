package com.htzw.transaction.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@TableName(value = "user")
public class User {
    /** 主键 **/
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /** 姓名 **/
    private String name;
    /** 岁数 **/
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

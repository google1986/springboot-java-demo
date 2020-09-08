package com.htzw.springboot.mybatis.model.many;

import com.htzw.springboot.mybatis.model.single.BaseInfo;
import com.htzw.springboot.mybatis.model.single.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfo extends User {

    /** 用户基本信息 */
    private BaseInfo baseInfo;

}

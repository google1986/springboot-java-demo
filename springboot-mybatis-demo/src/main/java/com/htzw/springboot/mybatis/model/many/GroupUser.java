package com.htzw.springboot.mybatis.model.many;

import com.htzw.springboot.mybatis.model.single.Group;
import com.htzw.springboot.mybatis.model.single.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Administrator
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupUser extends Group {

    /** 用户列表 */
    private List<User> users;

}

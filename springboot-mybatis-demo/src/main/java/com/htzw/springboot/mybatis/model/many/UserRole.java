package com.htzw.springboot.mybatis.model.many;

import com.htzw.springboot.mybatis.model.single.Role;
import com.htzw.springboot.mybatis.model.single.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends User {

    /** 角色列表 */
    private List<Role> roles;

}
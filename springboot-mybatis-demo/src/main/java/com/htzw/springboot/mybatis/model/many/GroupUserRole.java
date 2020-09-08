package com.htzw.springboot.mybatis.model.many;

import com.htzw.springboot.mybatis.model.single.Group;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupUserRole extends Group {

    /** 用户角色列表 */
    private List<UserRole> users;

}
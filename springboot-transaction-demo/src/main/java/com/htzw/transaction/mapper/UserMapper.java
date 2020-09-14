package com.htzw.transaction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.htzw.transaction.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

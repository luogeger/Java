package com.first.aop.mapper;

import com.first.aop.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 测试 User sql: but found 2 2
     * @param userId
     * @return
     */
    User selectUserById(String userId);
}

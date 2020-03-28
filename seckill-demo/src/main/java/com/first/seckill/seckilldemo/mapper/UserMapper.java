package com.first.seckill.seckilldemo.mapper;

import com.first.seckill.seckilldemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author luoxiaoqing
 */
@Mapper
public interface UserMapper {

    @Select("select * from User where id = #{id}")
    User selectOneById(Integer id);
}

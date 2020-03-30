package com.first.seckill.seckilldemo.mapper;

import com.first.seckill.seckilldemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author luoxiaoqing
 */
@Mapper
public interface UserMapper {

    @Select("select * from sec_user where id = #{id}")
    User selectOneById(Integer id);

    @Insert("insert into sec_user (id, name) values (#{id}, #{name})")
    int insert(User user);
}

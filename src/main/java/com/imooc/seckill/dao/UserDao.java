package com.imooc.seckill.dao;

import com.imooc.seckill.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by liushichang on 2019/3/20.
 */
@Mapper
public interface UserDao {

    @Select("select * from user where id=#{id}")
    User getUserById(@Param("id") int id);

    @Insert("insert into user(id,name)values(#{id},#{name})")
    int insert(User user);



}

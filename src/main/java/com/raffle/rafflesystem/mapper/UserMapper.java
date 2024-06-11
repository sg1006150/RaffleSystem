package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    @Select("select id,username,phone,email,type from user")
    List<User> getAllUser();
    @Select("select * from user where phone=#{Phone}")
    User findByPhone(String Phone);
    @Insert("insert into user(phone,password) values(#{phone},#{password})")
    int insertUser(User user);
    @Update("update user set password=#{password},email=#{email},username=#{username} where phone=#{phone}")
    int updateUser(User user);

}

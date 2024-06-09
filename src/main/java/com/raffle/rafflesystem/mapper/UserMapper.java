package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user")
    public List<User> getAllUser();
    @Select("select * from user where phone=#{Phone}")
    User findByPhone(String Phone);
    @Insert("insert into user(phone,password) values(#{phone},#{password})")
    int insertUser(User user);
}

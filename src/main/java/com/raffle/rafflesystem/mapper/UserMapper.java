package com.raffle.rafflesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.raffle.rafflesystem.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.BaseStream;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
//    @Select("select * from user")
//    public List<User> find();
//
//    @Insert("insert into user values (#{id}, #{username})")
//    public int insert(User user);
//
//    @Delete("delete from user where id = #{id}")
//    public int delete(int id);
}

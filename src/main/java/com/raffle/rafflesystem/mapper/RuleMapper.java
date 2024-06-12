package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.Rule;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RuleMapper {
    @Select("select * from rules")
    List<Rule> getAllRules();
    @Insert("insert into rules" +
            " (rulename,createdby,description,istemporary)" +
            " values (#{rulename},#{createdby},#{description}," +
            "#{istemporary})")
    int insertRule(Rule rule);

}

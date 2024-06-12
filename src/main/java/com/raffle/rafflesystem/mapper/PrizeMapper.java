package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.Prize;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Mapper
public interface PrizeMapper {
    @Select("select * from prize")
    List<Prize> getAllPrize();
    @Select("select * from prize where addedby=#{username}")
    List<Prize> getPrizeByUsername(String username);
    @Insert("insert into prize (picdirectory,name,price,addedby)" +
            "values (#{picdirectory},#{name},#{price},#{addedby})")
    int addPrize(Prize prize);
    @Delete("delete from prize where id=#{id}")
    int deletePrizeById(int id);
    @Update("update prize set picdirectory=#{picdirectory}," +
            "name=#{name},price=#{price} where id=#{id}")
    int updatePrize(Prize prize);

}

package com.raffle.rafflesystem.mapper;

import com.raffle.rafflesystem.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RuleMapper {
    @Select("select * from rules")
    List<Rule> getAllRules();
    @Select("select prize.name as prize_name from " +
            "rules join prize_rule on rules.id = prize_rule.rule_id " +
            "join prize on prize.id=prize_rule.prize_id")
    List<Prize>getPrizeByRuleId(int ruleId);
    @Select("select participants.name from " +
            "rules join participant_rule on rules.id = participant_rule.rule_id " +
            "join participants on participant_rule.participant_id = participants.id")
    List<Participant>getParticipantByRuleId(int ruleId);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into rules (rulename,createdby,description,istemporary)" +
            "values (#{rulename},#{createdby},#{description},#{istemporary})")
    int insertRule(Rule rule);
    @Insert("insert into participant_rule (participant_id, rule_id) " +
            "values (#{participant_id},#{rule_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertParticipantRule(Participant_rule participant_rule);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into prize_rule (prize_id, rule_id,prize_level,count) VALUES " +
            "(#{prize_id},#{rule_id},#{prize_level},#{count})")
    int insertPrizeRule(Prize_rule prizeRule);
    @Select("select * from rules where createdby=#{name}")
    List<Rule> getRulesByUserName(String name);
    @Select("select * from prize_rule where rule_id=#{id}")
    List<Prize_rule> getPrizeRuleByRule(Rule rule);
    @Select("select * from participant_rule where rule_id=#{id}")
    List<Participant_rule> getParticipantRuleByRule(Rule rule);
    @Select("select * from rules where createdby=#{username}")
    List<Rule> getRulesByUser(User user);
    @Select("select * from participants where addedby=#{username}")
    List<Participant> getParticipantByUser(User user);
    @Update("update participant_rule set participant_id=#{participant_id}," +
            " rule_id=#{rule_id} where id=#{id}")
    int updateParticipantRule(Participant_rule participant_rule);
    @Update("update prize_rule set prize_id=#{prize_id}," +
            "rule_id=#{rule_id},prize_level=#{prize_level}" +
            ",count=#{count} where id=#{id}")
    int updatePrizeRule(Prize_rule prizeRule);
    @Update("update rules set rulename=#{rulename},description=#{description}," +
            "istemporary=#{istemporary} where id=#{id}")
    int updateRule(Rule rule);
    @Delete("delete from prize_rule where id=#{id}")
    int deletePrizeRule(Prize_rule prizeRule);
    @Delete("delete from participant_rule where id=#{id}")
    int deleteParticipantRule(Participant_rule participantRule);
}

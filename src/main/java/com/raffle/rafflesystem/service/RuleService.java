package com.raffle.rafflesystem.service;

import com.raffle.rafflesystem.entity.*;
import com.raffle.rafflesystem.exception.ServiceException;
import com.raffle.rafflesystem.mapper.RuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
    @Autowired
    private RuleMapper ruleMapper;
    public List<Rule> getAllRules() {
        return ruleMapper.getAllRules();
    }
    public List<Prize> getPrizeByRuleId(int ruleId) {
        return ruleMapper.getPrizeByRuleId(ruleId);
    }
    public List<Participant>getParticipantByRuleId(int ruleId) {
        return ruleMapper.getParticipantByRuleId(ruleId);
    }
    public List<Rule>getRulesByUserName(String userName) {
        return ruleMapper.getRulesByUserName(userName);
    }
    public Rule addRule(Rule rule) {
       int state=ruleMapper.insertRule(rule);
       if(state==0){
           throw new ServiceException(402,"添加失败");
       }
       return rule;
    }
    public Participant_rule addParticipantRule(Participant_rule participantRule) {
        int state=ruleMapper.insertParticipantRule(participantRule);
        if(state==0){
            throw new ServiceException("添加失败");
        }
        return participantRule;
    }
    public Prize_rule addPrizeRule(Prize_rule prizeRule) {
        int state=ruleMapper.insertPrizeRule(prizeRule);
        if(state==0){
            throw new ServiceException("添加失败");
        }
        return prizeRule;
    }

}

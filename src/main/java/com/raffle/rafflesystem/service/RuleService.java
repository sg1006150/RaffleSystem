package com.raffle.rafflesystem.service;

import com.raffle.rafflesystem.entity.Rule;
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
}

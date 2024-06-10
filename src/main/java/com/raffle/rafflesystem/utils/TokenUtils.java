package com.raffle.rafflesystem.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.raffle.rafflesystem.entity.User;
import com.raffle.rafflesystem.mapper.UserMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

@Component
public class TokenUtils {
    static private UserMapper staticUserMapper;
    @Resource
    private UserMapper userMapper;
    @PostConstruct
    public void init() {
        staticUserMapper=userMapper;
    }
    public static String getToken(String phone,String password) {
        LocalDateTime now = LocalDateTime.now();  // 获取当前的日期和时间
        LocalDateTime inTwoHours = now.plusHours(2);  // 在当前时间基础上增加两小时

        // 将LocalDateTime转换为java.util.Date
        Date dateInTwoHours = Date.from(inTwoHours.atZone(ZoneId.systemDefault()).toInstant());
        return JWT.create().withAudience(phone)
                .withExpiresAt(dateInTwoHours)
                .sign(Algorithm.HMAC256(password));
    }
    public static User getUser() {
        try
        {
            HttpServletRequest request=((ServletRequestAttributes)
                    Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                    .getRequest();
            String token=request.getHeader("token");
            if(!Objects.equals(token, ""))
            {
                String phone=JWT.decode(token).getAudience().get(0);
                return staticUserMapper.findByPhone(phone);
            }
        }
        catch (Exception e)
        {
            return null;
        }
        return null;
    }
}

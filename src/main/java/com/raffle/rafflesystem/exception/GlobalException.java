package com.raffle.rafflesystem.exception;

import com.raffle.rafflesystem.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//捕获全局异常
public class GlobalException {
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody//将返回值转换为json格式
    public Result serviceException(Exception e) {
        return Result.error(e.getMessage());
    }
}

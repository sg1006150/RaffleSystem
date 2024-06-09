package com.raffle.rafflesystem.common;

public class Result<T> {
    private boolean success;
    private String message;
    private T data;
    private Integer errorCode; // 可能为null，用于表示没有错误码

    // 无参数构造器
    public Result() {}

    // 完整参数构造器
    public Result(boolean success, String message, T data, Integer errorCode) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errorCode = errorCode;
    }

    // 为成功结果创建快捷方法
    public static <T> Result<T> success(T data) {
        return new Result<>(true, "Operation successful", data, null);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, message, data, null);
    }

    // 为错误结果创建快捷方法，包含错误码
    public static <T> Result<T> error(String message, Integer errorCode) {
        return new Result<>(false, message, null, errorCode);
    }

    // Getters 和 Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}

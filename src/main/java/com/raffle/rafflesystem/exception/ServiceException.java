package com.raffle.rafflesystem.exception;

public class ServiceException extends RuntimeException{
    private int code;
    public ServiceException(String message) {
        super(message);
        this.code=500;
    }
    public ServiceException(int code, String message) {
        super(message);
        this.code=code;
    }
    public int getCode() {return this.code;}
}

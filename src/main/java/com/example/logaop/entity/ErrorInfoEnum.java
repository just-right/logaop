package com.example.logaop.entity;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum ErrorInfoEnum {
    DEFAULT(999,"default"),ERROR(100,"服务器异常！"),NOTFOUND(101,"资源丢失！");
    @Getter private Integer statusCode;
    @Getter private String errorInfo;

    ErrorInfoEnum(Integer statusCode, String errorInfo) {
        this.statusCode = statusCode;
        this.errorInfo = errorInfo;
    }


    public static ErrorInfoEnum getErrorByCode(Integer code){
       Optional<ErrorInfoEnum> optional = Arrays.stream(ErrorInfoEnum.values()).filter(e->e.getStatusCode().equals(code)).findFirst();
       return optional.orElse(DEFAULT);
    }
}

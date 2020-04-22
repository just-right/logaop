package com.example.logaop.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * (LogAopInfo)实体类
 *
 * @author makejava
 * @since 2020-04-21 20:47:01
 */
@Data
@Accessors(chain = true)
@Component
public class LogAopInfo implements Serializable {
    private static final long serialVersionUID = -64542237845165180L;
    
    private Integer id;

    private String sessionID;
    
    private String requestUri;
    
    private String method;
    
    private String parameterMapStr;
    
    private String ipAddress;
    
    private LocalDateTime beginDateTime;
    
    private LocalDateTime endDateTime;
    
    private Integer spendTimes;
    
    private Integer statusCode;
    
    private String responseInfo;

    private String error;
    
    private String exception;
    
    private String errorMsg;

    public LogAopInfo() {
        super();
    }
}
package com.example.logaop.aspect;

public interface ILogAspectConst {
    String REQUEST_ATTRIBUTE = "RequestInfo";
    String CONTROLLER_EXECUTION = "execution(* com.example.logaop.controller..*.*(..))";
    String REQUEST_TOKEN_FIELD = "token";
    String TOKEN_INFO = "hello";
    String CONTENT_TYPE = "application/json; charset=utf-8";
    String TIP_MSG = "访问信息未携带有效token!";
}

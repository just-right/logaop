package com.example.logaop.controller;

import com.example.logaop.annotation.LogAop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: TestController
 * @description
 * @author: luffy
 * @date: 2020/4/16 12:28
 * @version:V1.0
 */
@RestController
@RequestMapping(value = "/logaop")
public class LogAopController {

    @LogAop
    @GetMapping(value = "/test")
    public String logAopTest() {
        int a = 1/0;  //异常测试
        return "hello wolrd!";
    }


    @LogAop(logFlag = false)
    @GetMapping(value = "/test2")
    public String logAopTest2() {
        return "hello wolrd!";
    }
}

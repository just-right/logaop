package com.example.logaop.config;

import com.example.logaop.interceptor.LoggerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: LoggerInterceptorConfigurer
 * @description
 * @author: luffy
 * @date: 2020/4/17 18:31
 * @version:V1.0
 */
@Configuration
public class LoggerInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
    }
}

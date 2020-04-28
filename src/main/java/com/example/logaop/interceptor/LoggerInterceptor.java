package com.example.logaop.interceptor;

import com.example.logaop.annotation.LogAop;
import com.example.logaop.aspect.ILogAspectConst;
import com.sun.org.apache.xpath.internal.operations.Bool;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * @className: LoggerInterceptor
 * @description
 * @author: luffy
 * @date: 2020/4/17 12:56
 * @version:V1.0
 */
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LogAop loginCheck = handlerMethod.getMethod().getAnnotation(LogAop.class);
        if (loginCheck != null && loginCheck.logFlag()) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Optional<Boolean> optional = parameterMap.entrySet().stream()
                    .filter(e -> e.getKey().equals(ILogAspectConst.REQUEST_TOKEN_FIELD)).findFirst()
                    .map(e -> e.getValue()[0].startsWith(ILogAspectConst.TOKEN_INFO));
            if (optional.orElse(false)) {
                return true;
            }
            response.setContentType(ILogAspectConst.CONTENT_TYPE);
            response.getWriter().println(ILogAspectConst.TIP_MSG);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}

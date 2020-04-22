package com.example.logaop.aspect;

import com.alibaba.fastjson.JSONObject;
import com.example.logaop.dao.LogAopInfoDao;
import com.example.logaop.entity.ErrorInfoEnum;
import com.example.logaop.entity.LogAopInfo;
import com.example.logaop.util.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @className: LogAspect
 * @description
 * @author: luffy
 * @date: 2020/4/17 19:26
 * @version:V1.0
 */
@Component
@Aspect
public class LogAspect  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private LogAopInfoDao dao;


    @Pointcut(ILogAspectConst.CONTROLLER_EXECUTION)
    private void pointCutMethod() {

    }


    @Before( value = "pointCutMethod()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //请求SessionID
        String sessionID = request.getRequestedSessionId();
        //请求路径
        String requestURI = request.getRequestURI();
        //获取请求参数信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        String jsonStr = JSONObject.toJSONString(parameterMap);
        //获取IP地址
        String ipAddress = IPUtils.getIpAddr(request);
        //获取请求方法
        String method = request.getMethod();
        //访问时间
        LocalDateTime dateTime = LocalDateTime.now();

        LogAopInfo logEntity = new LogAopInfo();
        logEntity.setSessionID(sessionID).setRequestUri(requestURI)
                .setParameterMapStr(jsonStr).setIpAddress(ipAddress)
                .setMethod(method).setBeginDateTime(dateTime);

        request.setAttribute(ILogAspectConst.REQUEST_ATTRIBUTE, logEntity);

    }

    @AfterReturning(value = "pointCutMethod()", returning = "keys")
    public void doAfterReturning(JoinPoint joinPoint, Object keys) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        LogAopInfo logEntity = (LogAopInfo) request.getAttribute(ILogAspectConst.REQUEST_ATTRIBUTE);
        String responseInfo =  JSONObject.toJSONString(keys);
        logEntity.setResponseInfo(responseInfo);
        logger.info(logEntity.toString());
        dao.insert(logEntity);
    }


    @After(value = "pointCutMethod()")
    public void doAfter(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        HttpServletRequest request = requestAttributes.getRequest();
        LogAopInfo logEntity = (LogAopInfo) request.getAttribute(ILogAspectConst.REQUEST_ATTRIBUTE);
        //返回状态
        int status = response.getStatus();
        //请求结束时间
        LocalDateTime endDateTime = LocalDateTime.now();
        Duration duration = Duration.between(logEntity.getBeginDateTime(),endDateTime);

        //请求耗时
        long spendTimes = duration.getSeconds();
        logEntity.setStatusCode(status).setEndDateTime(endDateTime)
                .setSpendTimes(new Long(spendTimes).intValue());

    }

//    @AfterThrowing(throwing = "ex",value = "pointCutMethod()")
//    public void doAfterThrowing(JoinPoint joinPoint,Throwable ex) throws IOException {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response = requestAttributes.getResponse();
//        HttpServletRequest request = requestAttributes.getRequest();
//        LogEntity logEntity = (LogEntity) request.getAttribute(ILogAspectConst.REQUEST_ATTRIBUTE);
//        logEntity.setError(true).setException(ex).setErrorMsg(ex.getMessage());
//        logger.error(logEntity.toString());
//    }


    //可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
    @Around(value = "pointCutMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        //获取访问方法名
        Signature signature = proceedingJoinPoint.getSignature();
        //获取请求参数
        Object[] params =  proceedingJoinPoint.getArgs();
        Object object  = null;
        try {
            //执行方法-返回值
             object =  proceedingJoinPoint.proceed();
        } catch (Throwable ex) {
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = requestAttributes.getRequest();
            LogAopInfo logEntity = (LogAopInfo) request.getAttribute(ILogAspectConst.REQUEST_ATTRIBUTE);
            logEntity.setError("true").setException(ex.getClass().toString()).setErrorMsg(ex.getMessage());
            this.printErrorStackTraceInfo(ex);
            object = ErrorInfoEnum.getErrorByCode(100).getErrorInfo();
        }
        return object;

    }

    private void printErrorStackTraceInfo(Throwable ex){
        if(ex != null){
            StringBuffer buffer = new StringBuffer();
            for (StackTraceElement element:ex.getStackTrace()) {
                buffer.append("\t at "+element.getClassName());
                buffer.append("."+element.getMethodName());
                buffer.append("("+element.getFileName()+":"+element.getLineNumber()+")\n");
            }
            logger.info(ex.getMessage()+"\n"+buffer.toString());
        }

    }


}

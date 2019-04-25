package com.hc.distributed.controllers.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class WebLogAOP {

//    @Pointcut("execution(public * com.qg.fantail.controllers.* .*(..))")
//    public void webLog(){}

    @Pointcut("execution(public * com.qg.fantail.controllers..*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("日志切面");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL：{}, HTTP_METHOD：{}, IP：{}, CLASS_METHOD：{}, ARGS：{}",
                request.getRequestURL().toString(), request.getMethod(), request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("RESPONSE : " + ret);
    }
}

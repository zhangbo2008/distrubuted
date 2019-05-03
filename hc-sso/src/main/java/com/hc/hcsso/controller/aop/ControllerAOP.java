package com.hc.hcsso.controller.aop;

import com.hc.hcsso.dtos.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.naming.NoPermissionException;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class ControllerAOP {

    @Around("execution(public com.hc.hcsso.dtos.ResultBean com.hc.hcsso..controllers..*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        log.info("异常切面");
        long startTime = System.currentTimeMillis();

        ResultBean<?> result;

        try {
            result = (ResultBean<?>) pjp.proceed();
            log.info(pjp.getSignature() + "use time:" + (System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            log.error("print the error parameter : " + Arrays.toString(pjp.getArgs()));
            result = handlerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 已知异常
        result.setMsg(e.getLocalizedMessage());
        if (e instanceof NoPermissionException) {
            result.setCode(ResultBean.NO_PERMISSION);
        } else {
            log.error(pjp.getSignature() + " error ：" + e.getMessage());
            e.printStackTrace();
            result.setCode(ResultBean.FAIL);
        }

        return result;
    }
}
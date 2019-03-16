package com.github.jcccode.springbootdemo.quartz.instrument;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
@Aspect
public class TraceQuartzAspect {

    private Logger log = LoggerFactory.getLogger(TraceQuartzAspect.class);

    @Around("execution(* org.springframework.scheduling.quartz.QuartzJobBean+.executeInternal(..))")
    public Object traceQuartzJob(final ProceedingJoinPoint pjp) throws Throwable {
        log.info("[Trace Quartz Aspect Works before]");
        try {
            return pjp.proceed();
        } finally {
            log.info("[Trace Quartz Aspect Works after]");
        }
    }
}

package com.github.jcccode.springbootdemo.quartz.instrument;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Component
@Aspect
public class TraceQuartzAspect {

    private Logger log = LoggerFactory.getLogger(TraceQuartzAspect.class);

    //@Before("execution(* org.springframework.scheduling.quartz.QuartzJobBean+.*(..))")
    //@Before("execution(* *.executeInternal(..))")
    //@Before("execution(public * org.springframework.scheduling.quartz.QuartzJobBean.execute(..))")
    //@Before("execution(* org.springframework.scheduling.quartz.QuartzJobBean+.execute(..))")
    //@Before("execution(public * com.github.jcccode.springbootdemo.quartz.job.SampleJob.*(..))")
    //@Before("execution(* org.springframework.scheduling.quartz.QuartzJobBean+.executeInternal(..))")
    public void traceQuartzJob(JoinPoint jp) throws Throwable {
        log.info("[[[Trace Quartz Aspect Works]]]");
    }
}

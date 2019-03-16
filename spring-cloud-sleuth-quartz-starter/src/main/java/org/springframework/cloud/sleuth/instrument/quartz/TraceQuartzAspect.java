package org.springframework.cloud.sleuth.instrument.quartz;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.util.SpanNameUtil;

import java.util.regex.Pattern;

/**
 * TraceQuartzAspect
 *
 * @author 01372461
 */
@Aspect
public class TraceQuartzAspect {

    private static final String QUARTZ_COMPONENT = "quartz";

    private final Tracer tracer;
    private final TraceKeys traceKeys;
    private final Pattern skipPattern;

    public TraceQuartzAspect(Tracer tracer, TraceKeys traceKeys, Pattern skipPattern) {
        this.tracer = tracer;
        this.traceKeys = traceKeys;
        this.skipPattern = skipPattern;
    }

    @Around("execution(* org.springframework.scheduling.quartz.QuartzJobBean+.executeInternal(..))")
    public Object traceQuartzJob(final ProceedingJoinPoint pjp) throws Throwable {
        if (this.skipPattern.matcher(pjp.getTarget().getClass().getCanonicalName()).matches()) {
            return pjp.proceed();
        }
        String spanName = SpanNameUtil.toLowerHyphen(pjp.getSignature().getName());
        Span span = this.tracer.createSpan(spanName);
        this.tracer.addTag(Span.SPAN_LOCAL_COMPONENT_TAG_NAME, QUARTZ_COMPONENT);
        this.tracer.addTag(this.traceKeys.getAsync().getPrefix() +
                this.traceKeys.getAsync().getClassNameKey(), pjp.getTarget().getClass().getSimpleName());
        this.tracer.addTag(this.traceKeys.getAsync().getPrefix() +
                this.traceKeys.getAsync().getMethodNameKey(), pjp.getSignature().getName());
        try {
            return pjp.proceed();
        } finally {
            this.tracer.close(span);
        }
    }
}

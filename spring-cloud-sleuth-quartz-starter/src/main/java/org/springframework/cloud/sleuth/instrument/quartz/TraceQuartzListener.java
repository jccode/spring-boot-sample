package org.springframework.cloud.sleuth.instrument.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.util.SpanNameUtil;

import java.util.regex.Pattern;

/**
 * TraceQuartzListener
 *
 * @author 01372461
 */
public class TraceQuartzListener implements JobListener {

    private static final String QUARTZ_COMPONENT = "quartz";

    private final Tracer tracer;
    private final TraceKeys traceKeys;
    private final Pattern skipPattern;

    private Span span;

    public TraceQuartzListener(Tracer tracer, TraceKeys traceKeys, Pattern skipPattern) {
        this.tracer = tracer;
        this.traceKeys = traceKeys;
        this.skipPattern = skipPattern;
    }

    @Override
    public String getName() {
        return "TraceQuartzListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        if (isSkipTracing(context)) return;

        String name = context.getJobDetail().getJobClass().getSimpleName();
        String spanName = SpanNameUtil.toLowerHyphen(name);
        this.span = this.tracer.createSpan(spanName);
        this.tracer.addTag(Span.SPAN_LOCAL_COMPONENT_TAG_NAME, QUARTZ_COMPONENT);
        this.tracer.addTag(this.traceKeys.getAsync().getPrefix() +
                this.traceKeys.getAsync().getClassNameKey(), name);
        this.tracer.addTag(this.traceKeys.getAsync().getPrefix() +
                this.traceKeys.getAsync().getMethodNameKey(), name);
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        if (isSkipTracing(context)) return;
        if (this.span != null) {
            this.tracer.close(span);
        }
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        if (isSkipTracing(context)) return;
        if (this.span != null) {
            this.tracer.close(span);
        }
    }


    private boolean isSkipTracing(JobExecutionContext context) {
        return this.skipPattern.matcher(context.getJobDetail().getJobClass().getCanonicalName()).matches();
    }
}

package org.springframework.cloud.sleuth.instrument.quartz;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.regex.Pattern;

/**
 * Register beans for quartz tracing
 *
 * @author 01372461
 */
@Configuration
@EnableAspectJAutoProxy
@ConditionalOnProperty(value = "spring.sleuth.quartz.enabled", matchIfMissing = true)
@ConditionalOnBean(Tracer.class)
@AutoConfigureAfter(TraceAutoConfiguration.class)
@EnableConfigurationProperties(SleuthQuartzProperties.class)
public class TraceQuartzAutoConfigration {

    @ConditionalOnClass(name = "org.aspectj.lang.ProceedingJoinPoint")
    @Bean
    public TraceQuartzAspect traceQuartzAspect(Tracer tracer, TraceKeys traceKeys,
                                               SleuthQuartzProperties sleuthQuartzProperties) {
        return new TraceQuartzAspect(tracer, traceKeys, Pattern.compile(sleuthQuartzProperties.getSkipPattern()));
    }

    @Bean
    public TraceQuartzListener traceQuartzListener(Tracer tracer, TraceKeys traceKeys,
                                                   SleuthQuartzProperties sleuthQuartzProperties) {
        return new TraceQuartzListener(tracer, traceKeys, Pattern.compile(sleuthQuartzProperties.getSkipPattern()));
    }
}

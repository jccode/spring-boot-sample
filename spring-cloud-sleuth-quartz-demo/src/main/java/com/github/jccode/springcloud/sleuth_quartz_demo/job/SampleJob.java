package com.github.jccode.springcloud.sleuth_quartz_demo.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
//@DisallowConcurrentExecution
public class SampleJob extends QuartzJobBean {

    private static final Logger log = LoggerFactory.getLogger(SampleJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("sample job running");
    }

}

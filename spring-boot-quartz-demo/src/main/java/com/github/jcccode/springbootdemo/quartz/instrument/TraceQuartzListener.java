package com.github.jcccode.springbootdemo.quartz.instrument;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TraceQuartzListener
 *
 * @author 01372461
 */
public class TraceQuartzListener implements JobListener {

    private static final Logger log = LoggerFactory.getLogger(TraceQuartzListener.class);

    @Override
    public String getName() {
        return "TraceQuartzListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("job to be executed");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("job execution vetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("job was executed");
    }
}

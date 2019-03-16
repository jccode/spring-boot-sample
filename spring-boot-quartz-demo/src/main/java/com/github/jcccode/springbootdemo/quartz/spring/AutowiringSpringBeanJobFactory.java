package com.github.jcccode.springbootdemo.quartz.spring;

import org.quartz.Job;
import org.quartz.SchedulerContext;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public final class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(AutowiringSpringBeanJobFactory.class);

    private transient AutowireCapableBeanFactory beanFactory;

    private String[] ignoredUnknownProperties;

    private SchedulerContext schedulerContext;


    /**
     * Specify the unknown properties (not found in the bean) that should be ignored.
     * <p>Default is {@code null}, indicating that all unknown properties
     * should be ignored. Specify an empty array to throw an exception in case
     * of any unknown properties, or a list of property names that should be
     * ignored if there is no corresponding property found on the particular
     * job class (all other unknown properties will still trigger an exception).
     */
    public void setIgnoredUnknownProperties(String... ignoredUnknownProperties) {
        this.ignoredUnknownProperties = ignoredUnknownProperties;
    }

    @Override
    public void setSchedulerContext(SchedulerContext schedulerContext) {
        this.schedulerContext = schedulerContext;
    }

    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = instantiateJobClass(bundle);
        beanFactory.autowireBean(job);
        populateSchedulerProperties(bundle, job);
        return job;
    }

    /**
     * Instantiate job class
     *
     * @param bundle
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    protected Object instantiateJobClass(final TriggerFiredBundle bundle) throws IllegalAccessException, InstantiationException {
        Class<? extends Job> jobClass = bundle.getJobDetail().getJobClass();
        Job job = null;
        try {
            job = beanFactory.getBean(jobClass);
        } catch (BeansException e) {
            log.warn("Instantiate job [{}] by newInstance instead of take it from Spring context. " +
                            "Make sure mark this jobClass with annotation (@Component) as a Bean of Spring.",
                    this.getClass().getName());
            job = jobClass.newInstance();
        }
        return job;
    }

    /**
     *  Populating job instance with property values taken
     * 	from the scheduler context, job data map and trigger data map.
     *
     * @param bundle
     * @param job
     */
    protected void populateSchedulerProperties(final TriggerFiredBundle bundle, Object job) {
        if (isEligibleForPropertyPopulation(job)) {
            BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(job);
            MutablePropertyValues pvs = new MutablePropertyValues();
            if (this.schedulerContext != null) {
                pvs.addPropertyValues(this.schedulerContext);
            }
            pvs.addPropertyValues(bundle.getJobDetail().getJobDataMap());
            pvs.addPropertyValues(bundle.getTrigger().getJobDataMap());
            if (this.ignoredUnknownProperties != null) {
                for (String propName : this.ignoredUnknownProperties) {
                    if (pvs.contains(propName) && !bw.isWritableProperty(propName)) {
                        pvs.removePropertyValue(propName);
                    }
                }
                bw.setPropertyValues(pvs);
            }
            else {
                bw.setPropertyValues(pvs, true);
            }
        }
    }

}
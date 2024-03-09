package dev.hieplp.spring.scheduler.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
public class QuartzConfig {
//    @Bean
//    public Scheduler scheduler() throws SchedulerException {
//        final var schedulerFactory = new StdSchedulerFactory();
//        final var scheduler = schedulerFactory.getScheduler();
//        scheduler.start();
//        return scheduler;
//    }


    @Bean
    public SchedulerFactoryBean schedulerFactory(ApplicationContext applicationContext) {
        final var factoryBean = new SchedulerFactoryBean();

        final var jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        factoryBean.setJobFactory(jobFactory);
        factoryBean.setApplicationContextSchedulerContextKey("applicationContext");

        return factoryBean;
    }

    public static class AutowiringSpringBeanJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

        private transient AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            beanFactory = applicationContext.getAutowireCapableBeanFactory();
        }

        @Override
        protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
            final var job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }

    }
}

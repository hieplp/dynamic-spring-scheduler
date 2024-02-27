package dev.hieplp.spring.scheduler.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public Scheduler scheduler() throws SchedulerException {
        final var schedulerFactory = new StdSchedulerFactory();
        final var scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        return scheduler;
    }
}

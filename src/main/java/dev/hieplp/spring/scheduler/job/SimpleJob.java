package dev.hieplp.spring.scheduler.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        log.info("SimpleJob is running");
    }
}
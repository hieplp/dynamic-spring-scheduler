package dev.hieplp.spring.scheduler.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;

@Slf4j
public class SimpleJob implements Job {
    @Override
    public void execute(org.quartz.JobExecutionContext context) {
        log.info("Execute job");
    }
}
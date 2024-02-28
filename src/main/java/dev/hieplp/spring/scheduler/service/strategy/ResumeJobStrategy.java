package dev.hieplp.spring.scheduler.service.strategy;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ResumeJobStrategy extends AbstractJobInteractionStrategy {
    @Override
    protected void interaction(JobKey jobKey) throws SchedulerException {
        log.debug("Resume job with jobKey: {}", jobKey);
        checkJobExists(jobKey);
        scheduler.resumeJob(jobKey);
    }
}

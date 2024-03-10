package dev.hieplp.spring.scheduler.service.strategy;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StartJobStrategy extends AbstractJobInteractionStrategy {
    @Override
    protected void interact(JobKey jobKey) throws SchedulerException {
        log.debug("Start job with jobKey: {}", jobKey);
        checkJobExists(jobKey);
        scheduler.triggerJob(jobKey);
    }
}

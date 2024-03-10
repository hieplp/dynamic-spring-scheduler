package dev.hieplp.spring.scheduler.service.strategy;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteJobStrategy extends AbstractJobInteractionStrategy {
    @Override
    protected void interact(JobKey jobKey) throws SchedulerException {
        log.info("Delete job with key: {}", jobKey);
        checkJobExists(jobKey);
        scheduler.deleteJob(jobKey);
    }
}

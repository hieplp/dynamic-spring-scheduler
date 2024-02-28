package dev.hieplp.spring.scheduler.service.strategy;

import dev.hieplp.spring.scheduler.common.exception.NotFoundException;
import dev.hieplp.spring.scheduler.common.exception.UnknownException;
import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractJobInteractionStrategy {

    @Autowired
    protected Scheduler scheduler;

    public JobKey interact(InteractJobRequest request) {
        try {
            final var jobKey = getJobKey(request);
            interaction(jobKey);
            return jobKey;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new UnknownException(e.getMessage());
        }
    }

    protected abstract void interaction(JobKey jobKey) throws SchedulerException;

    protected JobKey getJobKey(InteractJobRequest request) {
        return JobKey.jobKey(request.getName(), request.getGroup());
    }

    protected void checkJobExists(JobKey jobKey) throws SchedulerException {
        if (!scheduler.checkExists(jobKey)) {
            throw new NotFoundException("Job not found");
        }
    }
}

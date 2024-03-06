package dev.hieplp.spring.scheduler.service.impl;

import dev.hieplp.spring.scheduler.common.enums.file.JobType;
import dev.hieplp.spring.scheduler.common.exception.DuplicateException;
import dev.hieplp.spring.scheduler.common.exception.UnknownException;
import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.UpdateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;
import dev.hieplp.spring.scheduler.common.payload.response.job.UpdateJobResponse;
import dev.hieplp.spring.scheduler.service.JobService;
import dev.hieplp.spring.scheduler.service.strategy.JobInteractionFactory;
import dev.hieplp.spring.scheduler.service.strategy.JobInteractionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;
    private final JobInteractionFactory interactionFactory;

    @Override
    public CreateJobResponse create(CreateJobRequest request) {
        try {
            log.info("Create job with request: {}", request);

            final var jobKey = JobKey.jobKey(request.getName(), request.getGroup());
            if (scheduler.checkExists(jobKey)) {
                throw new DuplicateException("Job already exists");
            }

            final var jobType = JobType.valueOf(request.getType());
            var job = JobBuilder.newJob(jobType.getClazz())
                    .withIdentity(jobKey)
                    .withDescription(request.getDescription())
                    .build();

            var trigger = TriggerBuilder.newTrigger()
                    .withIdentity(request.getName(), request.getGroup())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(request.getCronExpression()))
                    .build();

            scheduler.scheduleJob(job, trigger);

            var response = new CreateJobResponse();
            BeanUtils.copyProperties(request, response);
            return response;
        } catch (SchedulerException e) {
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public UpdateJobResponse update(UpdateJobRequest request) {
        log.info("Update job with request: {}", request);

        interact(InteractJobRequest.builder()
                .name(request.getName())
                .group(request.getGroup())
                .type(JobInteractionType.DELETE)
                .build());

        create(CreateJobRequest.builder()
                .name(request.getName())
                .group(request.getGroup())
                .description(request.getDescription())
                .cronExpression(request.getCronExpression())
                .type(request.getType())
                .build());

        var response = new UpdateJobResponse();
        BeanUtils.copyProperties(request, response);
        return response;
    }

    @Override
    public JobModel interact(InteractJobRequest request) {
        log.info("Interact job with request: {}", request);
        final var service = interactionFactory.getInstance(request.getType());
        final var jobKey = service.interact(request);
        return get(jobKey);
    }

    @Override
    public List<JobModel> getAll() {
        try {
            log.info("Get all jobs");
            final var list = new ArrayList<JobModel>();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    list.add(get(jobKey));
                }
            }
            return list;
        } catch (SchedulerException e) {
            throw new UnknownException(e.getMessage());
        }
    }

    @Override
    public JobModel get(String group, String name) {
        log.info("Get job with group: {} and name: {}", group, name);
        final var jobKey = JobKey.jobKey(name, group);
        return get(jobKey);
    }

    @Override
    public JobModel get(JobKey jobKey) {
        try {
            log.info("Get job with jobKey: {}", jobKey);

            final var jobDetail = scheduler.getJobDetail(jobKey);
            final var triggers = scheduler.getTriggersOfJob(jobKey);

            final var firstTrigger = triggers.getFirst();
            final var state = scheduler.getTriggerState(firstTrigger.getKey());

            return JobModel.builder()
                    .name(jobDetail.getKey().getName())
                    .group(jobDetail.getKey().getGroup())
                    .description(jobDetail.getDescription())
                    .cronExpression(((CronTrigger) firstTrigger).getCronExpression())
                    .state(state)
                    .build();
        } catch (NoSuchElementException e) {
            return null;
        } catch (SchedulerException e) {
            throw new UnknownException(e.getMessage());
        }
    }
}

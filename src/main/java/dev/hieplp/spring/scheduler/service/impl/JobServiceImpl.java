package dev.hieplp.spring.scheduler.service.impl;

import dev.hieplp.spring.scheduler.common.enums.file.JobType;
import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;
import dev.hieplp.spring.scheduler.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final Scheduler scheduler;

    @Override
    public CreateJobResponse create(CreateJobRequest request) {
        try {
            log.info("Create job with request: {}", request);

            final var jobType = JobType.valueOf(request.getType());

            var job = JobBuilder.newJob(jobType.getClazz())
                    .withIdentity(request.getName(), request.getGroup())
                    .withDescription(request.getDescription())
                    .build();

            var trigger = TriggerBuilder.newTrigger()
                    .withIdentity(request.getName(), request.getGroup())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(request.getCronExpression()))
                    .build();

            scheduler.scheduleJob(job, trigger);

            return CreateJobResponse.builder()
                    .name(request.getName())
                    .group(request.getGroup())
                    .description(request.getDescription())
                    .cronExpression(request.getCronExpression())
                    .build();
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<JobModel> getAll() {
        try {
            log.info("Get all jobs");
            final var list = new ArrayList<JobModel>();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    final var jobDetail = scheduler.getJobDetail(jobKey);
                    final var triggers = scheduler.getTriggersOfJob(jobKey);
                    list.add(JobModel.builder()
                            .name(jobDetail.getKey().getName())
                            .group(jobDetail.getKey().getGroup())
                            .description(jobDetail.getDescription())
                            .cronExpression(((CronTrigger) triggers.getFirst()).getCronExpression())
                            .build()
                    );
                }
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

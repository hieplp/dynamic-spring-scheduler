package dev.hieplp.spring.scheduler.service;

import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.UpdateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;
import dev.hieplp.spring.scheduler.common.payload.response.job.UpdateJobResponse;
import org.quartz.JobKey;

import java.util.List;

public interface JobService {
    CreateJobResponse create(CreateJobRequest request);

    UpdateJobResponse update(UpdateJobRequest request);

    JobModel interact(InteractJobRequest request);

    List<JobModel> getAll();

    JobModel get(String group, String name);

    JobModel get(JobKey jobKey);
}

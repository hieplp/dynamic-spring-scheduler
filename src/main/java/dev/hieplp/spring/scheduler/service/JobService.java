package dev.hieplp.spring.scheduler.service;

import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.UpdateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;
import dev.hieplp.spring.scheduler.common.payload.response.job.UpdateJobResponse;
import org.quartz.JobKey;

import java.util.List;
import java.util.Optional;

public interface JobService {
    CreateJobResponse create(CreateJobRequest request);

    UpdateJobResponse update(UpdateJobRequest request);

    Optional<JobModel> interact(InteractJobRequest request);

    List<JobModel> getAll();

    Optional<JobModel> get(String group, String name);

    Optional<JobModel> get(JobKey jobKey);
}

package dev.hieplp.spring.scheduler.service;

import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;

import java.util.List;

public interface JobService {
    CreateJobResponse create(CreateJobRequest request);

    List<JobModel> getAll();
}

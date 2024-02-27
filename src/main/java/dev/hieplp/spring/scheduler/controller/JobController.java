package dev.hieplp.spring.scheduler.controller;


import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.CommonResponse;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;
import dev.hieplp.spring.scheduler.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @PostMapping
    public CommonResponse<CreateJobResponse> create(@RequestBody CreateJobRequest request) {
        log.debug("Create job with request: {}", request);
        final var response = jobService.create(request);
        return CommonResponse.success(response);
    }

    @GetMapping
    public CommonResponse<List<JobModel>> getAll() {
        log.debug("Get all jobs");
        final var response = jobService.getAll();
        return CommonResponse.success(response);
    }
}

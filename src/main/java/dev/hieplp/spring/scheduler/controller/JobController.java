package dev.hieplp.spring.scheduler.controller;


import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.UpdateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.response.CommonResponse;
import dev.hieplp.spring.scheduler.common.payload.response.job.CreateJobResponse;
import dev.hieplp.spring.scheduler.common.payload.response.job.UpdateJobResponse;
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

    @PutMapping("/{group}/{name}")
    public CommonResponse<UpdateJobResponse> update(@PathVariable String group,
                                                    @PathVariable String name,
                                                    @RequestBody UpdateJobRequest request) {
        log.debug("Update job with group: {}, name: {} and request: {}", group, name, request);

        request.setGroup(group);
        request.setName(name);
        final var response = jobService.update(request);

        return CommonResponse.success(response);
    }

    @PostMapping("/{group}/{name}/interact")
    public CommonResponse<Object> interact(@PathVariable String group,
                                           @PathVariable String name,
                                           @RequestBody InteractJobRequest request) {
        log.debug("Interact job with group: {}, name: {} and request: {}", group, name, request);

        request.setGroup(group);
        request.setName(name);
        final var jobModel = jobService.interact(request);

        return CommonResponse.success(jobModel);
    }
}

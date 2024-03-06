package dev.hieplp.spring.scheduler.controller;

import dev.hieplp.spring.scheduler.common.enums.file.JobType;
import dev.hieplp.spring.scheduler.common.payload.request.job.CreateJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import dev.hieplp.spring.scheduler.common.payload.request.job.UpdateJobRequest;
import dev.hieplp.spring.scheduler.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final JobService jobService;

    // --------------------------------------------------
    // ------------------- Index Page -------------------
    // --------------------------------------------------
    @GetMapping
    public String index(Model map) {
        log.info("Index page");

        final var jobs = jobService.getAll();
        map.addAttribute("jobs", jobs);

        return "index";
    }

    // --------------------------------------------------
    // ------------------- Create Job -------------------
    // --------------------------------------------------

    @GetMapping("/create")
    public String create(Model map) {
        log.info("Create page");

        final var types = JobType.values();
        map.addAttribute("types", types);

        return "create";
    }

    @PostMapping("/create")
    public String create(CreateJobRequest request) {
        log.info("Create job: {}", request);

        jobService.create(request);

        return "redirect:/";
    }

    // --------------------------------------------------
    // ------------------- Update Job -------------------
    // --------------------------------------------------

    @GetMapping("/update/{group}/{name}")
    public String update(Model map,
                         @PathVariable String group,
                         @PathVariable String name) {
        log.info("Update page");

        final var jobModel = jobService.get(group, name);
        map.addAttribute("job", jobModel);

        final var types = JobType.values();
        map.addAttribute("types", types);

        return "update";
    }

    @PostMapping("/update")
    public String update(UpdateJobRequest request) {
        log.info("Update job: {}", request);

        jobService.update(request);

        return "redirect:/";
    }

    // --------------------------------------------------
    // ------------------- Interact Job -----------------
    // --------------------------------------------------

    @PostMapping("/interact")
    public String interact(InteractJobRequest request) {
        log.info("Interact with job: {}", request);

        jobService.interact(request);

        return "redirect:/";
    }
}

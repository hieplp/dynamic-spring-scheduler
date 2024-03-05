package dev.hieplp.spring.scheduler.controller;

import dev.hieplp.spring.scheduler.common.payload.request.job.InteractJobRequest;
import dev.hieplp.spring.scheduler.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class IndexController {

    private final JobService jobService;

    @GetMapping
    public String index(Model map) {
        log.info("Index page");

        final var jobs = jobService.getAll();
        map.addAttribute("jobs", jobs);

        return "index";
    }

    @GetMapping("/create")
    public String create(Model map) {
        log.info("Create page");
        return "create";
    }

    @PostMapping("/interact")
    public String interact(InteractJobRequest request) {
        log.info("Interact with job: {}", request);

        jobService.interact(request);

        return "redirect:/";
    }
}

package dev.hieplp.spring.scheduler.controller;

import dev.hieplp.spring.scheduler.common.model.JobModel;
import dev.hieplp.spring.scheduler.service.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

        var jobs = jobService.getAll();
        map.addAttribute("jobs", jobs);

        map.addAttribute("job", new JobModel());

        return "index";
    }

    @PostMapping("/interact")
    public String interact(@ModelAttribute("job") JobModel job) {
        log.info("Interact job: {}", job);
        return "redirect:/";
    }
}

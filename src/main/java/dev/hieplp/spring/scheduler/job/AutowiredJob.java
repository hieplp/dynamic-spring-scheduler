package dev.hieplp.spring.scheduler.job;

import dev.hieplp.spring.scheduler.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class AutowiredJob implements Job {

    private MockService mockService;

    @Autowired
    private void setMockService(MockService mockService) {
        this.mockService = mockService;
    }

    @Override
    public void execute(JobExecutionContext context) {
        log.info("AutowiredJob is running");
        mockService.logRandomThings();
    }
}

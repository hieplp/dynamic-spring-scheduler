package dev.hieplp.spring.scheduler.service.impl;

import dev.hieplp.spring.scheduler.service.MockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockServiceImpl implements MockService {
    @Override
    public void logRandomThings() {
        log.info("This is a debug message and this is a random number: " + Math.random());
    }
}

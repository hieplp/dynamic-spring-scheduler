package dev.hieplp.spring.scheduler.service.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobInteractionFactory {

    private final StartJobStrategy startJobStrategy;
    private final PauseJobStrategy pauseJobStrategy;
    private final ResumeJobStrategy resumeJobStrategy;
    private final DeleteJobStrategy deleteJobStrategy;

    public AbstractJobInteractionStrategy getInstance(JobInteractionType type) {
        return switch (type) {
            case START -> startJobStrategy;
            case PAUSE -> pauseJobStrategy;
            case RESUME -> resumeJobStrategy;
            case DELETE -> deleteJobStrategy;
            default -> throw new IllegalArgumentException("Invalid job interaction type: " + type);
        };
    }
}

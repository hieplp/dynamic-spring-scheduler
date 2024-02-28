package dev.hieplp.spring.scheduler.common.payload.request.job;

import dev.hieplp.spring.scheduler.service.strategy.JobInteractionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InteractJobRequest {
    private String name;
    private String group;
    private JobInteractionType type;
}

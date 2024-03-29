package dev.hieplp.spring.scheduler.common.payload.request.job;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateJobRequest {
    private String name;
    private String group;
    private String description;
    private String cronExpression;
    private Integer type;
}

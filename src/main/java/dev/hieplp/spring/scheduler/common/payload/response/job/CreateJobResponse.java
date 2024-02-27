package dev.hieplp.spring.scheduler.common.payload.response.job;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateJobResponse {
    private String name;
    private String group;
    private String description;
    private String cronExpression;
}

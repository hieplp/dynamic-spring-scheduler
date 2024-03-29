package dev.hieplp.spring.scheduler.common.payload.request.job;

import lombok.Data;

@Data
public class UpdateJobRequest {
    private String name;
    private String group;
    private String description;
    private String cronExpression;
    private Integer type;
}

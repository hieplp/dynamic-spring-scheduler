package dev.hieplp.spring.scheduler.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobModel {
    private String name;
    private String group;
    private String description;
    private String cronExpression;
    private Integer type;
}

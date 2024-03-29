package dev.hieplp.spring.scheduler.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.quartz.Trigger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobModel {
    private String name;
    private String group;
    private String description;
    private String cronExpression;
    private Integer type;
    private Trigger.TriggerState state;
}

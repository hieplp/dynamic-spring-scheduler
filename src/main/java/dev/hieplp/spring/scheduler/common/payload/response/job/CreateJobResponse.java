package dev.hieplp.spring.scheduler.common.payload.response.job;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobResponse {
    private String name;
    private String group;
    private String description;
    private String cronExpression;
}

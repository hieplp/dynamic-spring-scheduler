package dev.hieplp.spring.scheduler.common.enums.file;

import dev.hieplp.spring.scheduler.job.SimpleJob;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.quartz.Job;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum JobType {
    SIMPLE(0, SimpleJob.class),
    ;

    private final Integer type;
    private final Class<? extends Job> clazz;


    public static JobType valueOf(Integer type) {
        return Arrays.stream(values())
                .filter(jobType -> jobType.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}

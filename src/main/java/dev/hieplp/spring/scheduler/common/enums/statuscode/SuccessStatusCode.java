package dev.hieplp.spring.scheduler.common.enums.statuscode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatusCode implements StatusCode {
    SUCCESS("200", "Success"),
    ;

    private final String code;
    private final String message;
}

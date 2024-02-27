package dev.hieplp.spring.scheduler.common.enums.statuscode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatusCode implements StatusCode {
    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    ;

    private final String code;
    private final String message;
}

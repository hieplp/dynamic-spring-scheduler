package dev.hieplp.spring.scheduler.common.enums.statuscode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatusCode implements StatusCode {
    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    NOT_FOUND("404", "Not found"),
    DUPLICATE("409", "Duplicate");

    private final String code;
    private final String message;
}

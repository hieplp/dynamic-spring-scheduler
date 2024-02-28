package dev.hieplp.spring.scheduler.common.exception;

public class UnknownException extends BaseException {
    public UnknownException(String message) {
        super(message);
    }

    public UnknownException(String message, Throwable cause) {
        super(message, cause);
    }
}

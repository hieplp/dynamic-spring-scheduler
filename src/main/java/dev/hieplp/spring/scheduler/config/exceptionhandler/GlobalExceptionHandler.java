package dev.hieplp.spring.scheduler.config.exceptionhandler;

import dev.hieplp.spring.scheduler.common.enums.statuscode.ErrorStatusCode;
import dev.hieplp.spring.scheduler.common.exception.DuplicateException;
import dev.hieplp.spring.scheduler.common.exception.InvalidCronException;
import dev.hieplp.spring.scheduler.common.exception.NotFoundException;
import dev.hieplp.spring.scheduler.common.exception.UnknownException;
import dev.hieplp.spring.scheduler.common.payload.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CommonResponse<Object>> handleNotFoundException(NotFoundException e) {
        log.warn("NotFoundException: {}", e.getMessage());
        final var response = CommonResponse.error(ErrorStatusCode.NOT_FOUND);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<CommonResponse<Object>> handleDuplicateException(DuplicateException e) {
        log.warn("DuplicateException: {}", e.getMessage());
        final var response = CommonResponse.error(ErrorStatusCode.DUPLICATE);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(InvalidCronException.class)
    public ResponseEntity<CommonResponse<Object>> handleInvalidCronException(InvalidCronException e) {
        log.warn("InvalidCronException: {}", e.getMessage());
        final var response = CommonResponse.error(ErrorStatusCode.INVALID_CRON);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler({Exception.class, UnknownException.class})
    public ResponseEntity<CommonResponse<Object>> handleException(Exception e) {
        log.error("Exception: ", e);
        final var response = CommonResponse.error(ErrorStatusCode.INTERNAL_SERVER_ERROR);
        return ResponseEntity.ok(response);
    }
}

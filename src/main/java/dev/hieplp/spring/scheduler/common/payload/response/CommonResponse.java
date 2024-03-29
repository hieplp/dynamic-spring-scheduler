package dev.hieplp.spring.scheduler.common.payload.response;

import dev.hieplp.spring.scheduler.common.enums.statuscode.ErrorStatusCode;
import dev.hieplp.spring.scheduler.common.enums.statuscode.SuccessStatusCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .code(SuccessStatusCode.SUCCESS.getCode())
                .message(SuccessStatusCode.SUCCESS.getMessage())
                .data(data)
                .build();
    }

    public static CommonResponse<Object> error(ErrorStatusCode statusCode) {
        return CommonResponse.<Object>builder()
                .code(statusCode.getCode())
                .message(statusCode.getMessage())
                .data(new Object())
                .build();
    }
}

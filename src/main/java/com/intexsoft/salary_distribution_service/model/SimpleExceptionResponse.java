package com.intexsoft.salary_distribution_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
public class SimpleExceptionResponse {
    private String message;
    private int code;
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime time;

    private SimpleExceptionResponse() {
        time = LocalDateTime.now();
    }

    public SimpleExceptionResponse(HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
        code = httpStatus.value();
        message = "Unexpected exception";
    }

    public SimpleExceptionResponse(HttpStatus httpStatus, String message) {
        this(httpStatus);
        this.message = message;
    }

    public SimpleExceptionResponse(HttpStatus httpStatus, Exception exception) {
        this(httpStatus, exception.getMessage());
    }
}

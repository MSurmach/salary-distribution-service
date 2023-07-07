package com.intexsoft.salary_distribution_service.exception_handler;

import com.intexsoft.salary_distribution_service.exceptions.ResourceNotFoundException;
import com.intexsoft.salary_distribution_service.model.SimpleExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class SalaryDistributionExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SimpleExceptionResponse handleNotFoundException(HttpServletRequest request, ResourceNotFoundException exception) {
        logExceptionWithStackTrace(request, exception);
        return new SimpleExceptionResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<SimpleExceptionResponse> handleConstraintViolations(HttpServletRequest request, ConstraintViolationException exception) {
        logExceptionWithStackTrace(request, exception);
        return exception.getConstraintViolations()
                .stream().map(constraintViolation -> new SimpleExceptionResponse(HttpStatus.BAD_REQUEST,
                        constraintViolation.getMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<SimpleExceptionResponse> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        logExceptionWithStackTrace(request, exception);
        return exception.getBindingResult().getFieldErrors().
                stream()
                .map(fieldError -> new SimpleExceptionResponse(HttpStatus.BAD_REQUEST,
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public SimpleExceptionResponse handleAnyUncaughtException(HttpServletRequest request, Throwable exception) {
        logExceptionWithStackTrace(request, exception);
        return new SimpleExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private void logExceptionWithStackTrace(HttpServletRequest request, Throwable exception) {
        log.error("Failed to request \"{}\", {} is thrown", request.getRequestURL(), exception.getClass().getSimpleName(), exception);
    }
}

package com.rsa.expense.tracker.controller;

import com.rsa.expense.tracker.exception.CustomException;
import com.rsa.expense.tracker.exception.Error;
import com.rsa.expense.tracker.exception.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public ErrorResponse handleException(Exception ex) {
        return buildErrorResponse(Error.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return buildErrorResponse(Error.BAD_REQUEST, extractMessage(ex.getBindingResult()));
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    public ErrorResponse handleAuthenticationException(AuthenticationException ex) {
        return buildErrorResponse(Error.AUTHORIZATION_ERROR, ex.getMessage());
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        var error = ex.getError();
        return ResponseEntity.status(error.getStatus())
                .body(buildErrorResponse(error, ex.getMessage()));
    }

    private String extractMessage(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
    }

    private ErrorResponse buildErrorResponse(Error error, String message) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .code(error.getCode())
                .reason(error.getReason())
                .message(message)
                .build();
    }

}

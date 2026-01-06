package com.rsa.expense.tracker.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final Error error;

    public CustomException(Error error, String message) {
        super(message);
        this.error = error;
    }

}

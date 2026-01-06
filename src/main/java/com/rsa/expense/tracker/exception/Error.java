package com.rsa.expense.tracker.exception;

import lombok.Getter;

@Getter
public enum Error {
    BAD_REQUEST(400, "E001", "Bad request"),
    INTERNAL_SERVER_ERROR(500, "E002", "Internal server error"),
    CREATE_USER_ERROR(500, "E003", "Create user error"),
    AUTHORIZATION_ERROR(401, "E004", "Authorization error");

    private final int status;
    private final String code;
    private final String reason;

    Error(int status, String code, String reason) {
        this.status = status;
        this.code = code;
        this.reason = reason;
    }
}

package com.scheduler.api.common.exception;

public class AuthMissmatchPasswordException extends CustomException {
    public AuthMissmatchPasswordException() {
        super(ErrorCode.AUTH_MISMATCH_PASSWORD);
    }
}

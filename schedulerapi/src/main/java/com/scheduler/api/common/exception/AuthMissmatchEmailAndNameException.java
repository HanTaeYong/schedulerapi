package com.scheduler.api.common.exception;

public class AuthMissmatchEmailAndNameException extends CustomException {
    public AuthMissmatchEmailAndNameException() {
        super(ErrorCode.AUTH_MISMATCH_EMAIL_AND_NAME);
    }
}

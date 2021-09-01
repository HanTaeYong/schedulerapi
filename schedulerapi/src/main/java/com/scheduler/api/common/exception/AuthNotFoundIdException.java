package com.scheduler.api.common.exception;

public class AuthNotFoundIdException extends CustomException {
    public AuthNotFoundIdException() {
        super(ErrorCode.AUTH_NOT_FOUND_ID);
    }
}

package com.scheduler.api.common.exception;

public class MailFailedException extends CustomException {
    public MailFailedException() {
        super(ErrorCode.AGENDA_NOT_FOUND_ID);
    }
}

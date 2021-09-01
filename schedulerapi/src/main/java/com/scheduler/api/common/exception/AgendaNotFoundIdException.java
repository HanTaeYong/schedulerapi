package com.scheduler.api.common.exception;

public class AgendaNotFoundIdException extends CustomException {
    public AgendaNotFoundIdException() {
        super(ErrorCode.AGENDA_NOT_FOUND_ID);
    }
}

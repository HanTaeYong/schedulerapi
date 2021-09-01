package com.scheduler.api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    AUTH_NOT_FOUND_ID(-1, "아이디를 찾을 수 없습니다."),
    AUTH_MISMATCH_PASSWORD(-1, "비밀번호가 틀렸습니다."),
    AUTH_MISMATCH_EMAIL_AND_NAME(-1, "해당 이메일과 이름으로 등록된 정보를 찾을 수 없습니다."),
    AGENDA_NOT_FOUND_ID(-1, "일정 아이디를 찾을 수 없습니다."),
    SEND_MAIL_FAILED(-1, "메일 발송에 실패했습니다.");

    private final int code;
    private final String message;
}

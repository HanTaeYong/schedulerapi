package com.scheduler.api.common.exception;

import com.scheduler.api.common.model.response.CommonResult;
import com.scheduler.api.common.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.scheduler.api")
public class ExceptionAdvice {
    private final ResponseService responseService;

    @ExceptionHandler(AuthNotFoundIdException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult authNotFoundIdException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(ErrorCode.AUTH_NOT_FOUND_ID);
    }

    @ExceptionHandler(AuthMissmatchPasswordException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult authMissmatchPasswordException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(ErrorCode.AUTH_MISMATCH_PASSWORD);
    }

    @ExceptionHandler(AuthMissmatchEmailAndNameException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult AuthMissmatchEmailAndNameException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(ErrorCode.AUTH_MISMATCH_EMAIL_AND_NAME);
    }

    @ExceptionHandler(AgendaNotFoundIdException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult agendaNotFoundIdException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(ErrorCode.AGENDA_NOT_FOUND_ID);
    }

    @ExceptionHandler(MailFailedException.class)
    @ResponseStatus(HttpStatus.OK)
    protected CommonResult mailFailedException(HttpServletRequest request, Exception e){
        return responseService.getFailResult(ErrorCode.SEND_MAIL_FAILED);
    }
}

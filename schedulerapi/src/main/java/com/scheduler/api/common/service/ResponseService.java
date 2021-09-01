package com.scheduler.api.common.service;

import com.scheduler.api.common.exception.ErrorCode;
import com.scheduler.api.common.model.response.CommonResult;
import com.scheduler.api.common.model.response.ListResult;
import com.scheduler.api.common.model.response.SingleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    private MessageSource messageSource;

    // 단일건 결과를 처리하는 메소드
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setContent(data);
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    // 다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setContent(list);
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    // 성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        result.setCode(0);
        result.setMessage("success");
        return result;
    }

    // 실패 결과만 처리하는 메소드
    public CommonResult getFailResult(int code, String message) {
        CommonResult result = new CommonResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public CommonResult getFailResult(ErrorCode errorCode) {
        CommonResult result = new CommonResult();
        result.setCode(errorCode.getCode());
        result.setMessage(errorCode.getMessage());
        return result;
    }

}

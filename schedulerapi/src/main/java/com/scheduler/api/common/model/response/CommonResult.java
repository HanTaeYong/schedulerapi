package com.scheduler.api.common.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {
    
    @ApiModelProperty(position = 1, value = "응답 코드 : >= 0 정상, < 0 비정상")
    private int code;
    
    @ApiModelProperty(position = 2, value = "응답 메시지")
    private String message;
}

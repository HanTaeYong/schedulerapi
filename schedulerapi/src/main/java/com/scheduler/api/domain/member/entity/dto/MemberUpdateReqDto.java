package com.scheduler.api.domain.member.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(description = "사용자 업데이트 요청 정보")
@Data
public class MemberUpdateReqDto {
    @ApiModelProperty(position = 1, required = true, value = "이메일", example = "tyhan@cyglobal.net")
    @NotNull
    private String email;

    @ApiModelProperty(position = 2, required = true, value = "이름", example = "한태용")
    @NotNull
    private String name;

    @ApiModelProperty(position = 3, value = "별명", example = "용태한")
    private String nickname;

}

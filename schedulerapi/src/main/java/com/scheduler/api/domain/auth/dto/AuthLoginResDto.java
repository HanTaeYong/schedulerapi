package com.scheduler.api.domain.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(description = "인증 결과")
@Builder
@Data
public class AuthLoginResDto {

    @ApiModelProperty(position = 1, required = true, value = "token")
    @NotNull
    private String accessToken;

    @ApiModelProperty(position = 2, required = true, value = "ID", example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(position = 3, required = true, value = "사용자 ID", example = "tyhan92")
    @NotNull
    private String userId;

    @ApiModelProperty(position = 4, required = true, value = "사용자 이메일", example = "tyhan@cyglobal.net")
    @NotNull
    private String email;

    @ApiModelProperty(position = 5, required = true, value = "사용자 이름", example = "한태용")
    @NotNull
    private String name;

    @ApiModelProperty(position = 6, required = true, value = "사용자 닉네임", example = "용태한")
    @NotNull
    private String nickname;
}

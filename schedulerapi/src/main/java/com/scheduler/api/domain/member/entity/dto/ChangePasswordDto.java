package com.scheduler.api.domain.member.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(description = "비밀번호 변경")
@Data
public class ChangePasswordDto {
    @ApiModelProperty(required = true, value = "현재 비밀번호", example = "password")
    @NotNull
    private String currentPassword;

    @ApiModelProperty(required = true, value = "수정 비밀번호", example = "password")
    @NotNull
    private String newPassword;
}

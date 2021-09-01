package com.scheduler.api.domain.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "로그인")
@NoArgsConstructor
@Data
public class AuthLoginDto {

    @ApiModelProperty(position = 1, required = true, value = "사용자 아이디", example = "tyhan92")
    private String userId;

    @ApiModelProperty(position = 2, required = true, value = "사용자 비밀번호", example = "password")
    private String password;

}

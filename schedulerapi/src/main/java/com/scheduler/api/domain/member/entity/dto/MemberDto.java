package com.scheduler.api.domain.member.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "회원 가입 요청")
@Getter
@Setter
@NoArgsConstructor
public class MemberDto {

    @ApiModelProperty(position = 1, required = true, value = "아이디", example = "tyhan92")
    @NotNull
    private String userId;

    @ApiModelProperty(position = 2, required = true, value = "비밀번호", example = "password")
    @NotNull
    private String password;

    @ApiModelProperty(position = 3, required = true, value = "이메일", example = "tyhan@cyglobal.net")
    @NotNull
    private String email;

    @ApiModelProperty(position = 4, required = true, value = "이름", example = "한태용")
    @NotNull
    private String name;

    @ApiModelProperty(position = 5, value = "닉네임", example = "용태한")
    private String nickname;

}

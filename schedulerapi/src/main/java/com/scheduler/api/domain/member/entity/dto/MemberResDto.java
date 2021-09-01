package com.scheduler.api.domain.member.entity.dto;

import com.scheduler.api.domain.member.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@ApiModel(description = "사용자 조회 응답")
@Getter
public class MemberResDto {
    @ApiModelProperty(position = 1, required = true, value = "아이디", example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(position = 2, required = true, value = "사용자 아이디", example = "tyhan92")
    @NotNull
    private String userId;

    @ApiModelProperty(position = 3, required = true, value = "이메일", example = "tyhan@cyglobal.net")
    @NotNull
    private String email;

    @ApiModelProperty(position = 4, required = true, value = "이름", example = "한태용")
    @NotNull
    private String name;

    @ApiModelProperty(position = 5, required = true, value = "닉네임", example = "용태한")
    @NotNull
    private String nickname;

    public MemberResDto(Member entity) {
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.email = entity.getEmail();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
    }
}

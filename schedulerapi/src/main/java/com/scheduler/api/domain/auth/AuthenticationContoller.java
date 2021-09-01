package com.scheduler.api.domain.auth;

import com.scheduler.api.common.exception.MailFailedException;
import com.scheduler.api.common.model.response.CommonResult;
import com.scheduler.api.common.model.response.SingleResult;
import com.scheduler.api.common.service.ResponseService;
import com.scheduler.api.domain.auth.dto.AuthLoginDto;
import com.scheduler.api.domain.auth.dto.AuthLoginResDto;
import com.scheduler.api.domain.member.MemberService;
import com.scheduler.api.domain.member.entity.Member;
import com.scheduler.api.infra.mail.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Api(tags = {"00. 인증"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationContoller {

    private final ResponseService responseService;
    private final AuthenticationService authenticationService;
    private final MemberService memberService;
    private final MailService mailService;

    @ApiOperation(value = "로그인")
    @PostMapping(value = "/login")
    public SingleResult<AuthLoginResDto> login(@Valid @RequestBody AuthLoginDto reqDto) {
        return responseService.getSingleResult(authenticationService.login(reqDto));
    }

    @ApiOperation(value = "아이디 찾기")
    @GetMapping(value = "/id")
    public CommonResult findUserId(@ApiParam(required = true, value = "이메일") @RequestParam String email,
                                   @ApiParam(required = true, value = "이름") @RequestParam String name) {
        Member member = memberService.validMailAndName(email, name);

        String content = member.getUserId();
        if (mailService.sendMail(member.getEmail(), "아이디 찾기", content) == false) {
            throw new MailFailedException();
        }

        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "비밀번호 찾기")
    @GetMapping(value = "/password")
    public CommonResult findPassword(@ApiParam(required = true, value = "이메일") @RequestParam String email,
                                     @ApiParam(required = true, value = "이름") @RequestParam String name) {
        Member member = memberService.validMailAndName(email, name);

        String tempPassword = UUID.randomUUID().toString().replace("-","").substring(0, 10);
        String content = tempPassword;

        if (mailService.sendMail(member.getEmail(), "임시 비밀번호 발송", content) == false) {
            throw new MailFailedException();
        }
        memberService.changeTempPassword(member.getId(), tempPassword);

        return responseService.getSuccessResult();
    }

}

package com.scheduler.api.domain.member;

import com.scheduler.api.common.model.response.CommonResult;
import com.scheduler.api.common.service.ResponseService;
import com.scheduler.api.domain.member.entity.dto.ChangePasswordDto;
import com.scheduler.api.domain.member.entity.dto.MemberDto;
import com.scheduler.api.domain.member.entity.dto.MemberUpdateReqDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"01. 회원"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final ResponseService responseService;
    private final MemberService memberService;

    @ApiOperation(value = "회원 가입")
    @PostMapping(value = "")
    public CommonResult signUp (@ApiParam(required = true, value = "사용자 정보") @Valid @RequestBody MemberDto reqDto) {
        return responseService.getSingleResult(memberService.signUp(reqDto));
    }

    @ApiOperation(value = "회원 조회")
    @GetMapping(value = "/{id}")
    public CommonResult findUserById (@ApiParam(required = true, value = "사용자 아이디") @PathVariable Long id) {
        return responseService.getSingleResult(memberService.findById(id));
    }

    @ApiOperation(value = "회원 정보 수정")
    @PutMapping(value = "/{id}")
    public CommonResult update (@ApiParam(required = true, value = "사용자 아이디") @PathVariable Long id,
                                        @ApiParam(required = true, value = "사용자 업데이트 정보") @Valid @RequestBody MemberUpdateReqDto reqDto) {
        return responseService.getSingleResult(memberService.update(id, reqDto));
    }

    @ApiOperation(value = "비밀번호 변경")
    @PostMapping(value = "/{id}/password")
    public CommonResult changePassword (@ApiParam(required = true, value = "사용자 아이디") @PathVariable Long id,
                                                   @ApiParam(required = true, value = "비밀번호") @Valid @RequestBody ChangePasswordDto reqdto) {
        boolean result = memberService.changePassword(id, reqdto.getCurrentPassword(), reqdto.getNewPassword());

        if (!result) {
            return responseService.getFailResult(-1, "비밀번호 변경 실패.");
        }
        return responseService.getSuccessResult();
    }

    @ApiOperation(value = "아이디 중복체크")
    @GetMapping(value = "")
    public CommonResult getUserId (@ApiParam(required = true, value = "사용자 아이디") @RequestParam String userId) {
        boolean result = memberService.getUserId(userId);

        if (!result) {
            return responseService.getFailResult(-1, "이미 사용중인 ID입니다.");
        }
        return responseService.getSuccessResult();
    }

}

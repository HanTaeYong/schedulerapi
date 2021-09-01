package com.scheduler.api.domain.auth;

import com.scheduler.api.common.exception.AuthMissmatchPasswordException;
import com.scheduler.api.common.exception.AuthNotFoundIdException;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.common.model.response.CommonResult;
import com.scheduler.api.common.security.JwtTokenProvider;
import com.scheduler.api.domain.auth.dto.AuthLoginDto;
import com.scheduler.api.domain.auth.dto.AuthLoginResDto;
import com.scheduler.api.domain.member.entity.Member;
import com.scheduler.api.domain.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthLoginResDto login(@Valid @RequestBody AuthLoginDto reqDto) {

        Member member = memberRepository.findByUserIdAndIsDel(reqDto.getUserId(), ColDel.USE).orElseThrow(() -> new AuthNotFoundIdException());
        if (!passwordEncoder.matches(reqDto.getPassword(), member.getPassword())) {
            throw new AuthMissmatchPasswordException();
        }

        String token = jwtTokenProvider.createToken(String.valueOf(member.getId()));

        AuthLoginResDto authLoginResDto = AuthLoginResDto.builder()
                .accessToken(token)
                .id(member.getId())
                .userId(member.getUserId())
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .build();

        return authLoginResDto;
    }
}

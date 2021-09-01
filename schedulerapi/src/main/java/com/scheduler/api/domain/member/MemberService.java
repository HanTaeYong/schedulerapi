package com.scheduler.api.domain.member;

import com.scheduler.api.common.exception.AuthMissmatchEmailAndNameException;
import com.scheduler.api.common.exception.AuthMissmatchPasswordException;
import com.scheduler.api.common.exception.AuthNotFoundIdException;
import com.scheduler.api.common.model.jenum.ColDel;
import com.scheduler.api.domain.member.entity.Member;
import com.scheduler.api.domain.member.entity.MemberRepository;
import com.scheduler.api.domain.member.entity.dto.MemberDto;
import com.scheduler.api.domain.member.entity.dto.MemberResDto;
import com.scheduler.api.domain.member.entity.dto.MemberUpdateReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member signUp(MemberDto reqDto) {
        if(memberRepository.findByUserIdAndIsDel(reqDto.getUserId(), ColDel.USE).isPresent()) {
            throw new RuntimeException("이미 존재하는 아이디입니다.");
        }

        String tmpNickname = reqDto.getNickname().isBlank() ? reqDto.getName() : reqDto.getNickname();

        Member member = Member.builder()
                .userId(reqDto.getUserId())
                .password(passwordEncoder.encode(reqDto.getPassword()))
                .email(reqDto.getEmail())
                .name(reqDto.getName())
                .nickname(tmpNickname)
                .isDel(ColDel.USE)
                .build();

        return memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberResDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new AuthNotFoundIdException());

        return new MemberResDto(member);
    }

    @Transactional
    public Member update(Long id, MemberUpdateReqDto reqDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new AuthNotFoundIdException());
        member.update(reqDto);

        return member;
    }

    @Transactional
    public boolean changePassword(Long id, String currentPassword, String newPassword) {
        Member member = memberRepository.findById(id).orElseThrow(()->new AuthNotFoundIdException());
        if (!passwordEncoder.matches(currentPassword, member.getPassword())) {
            throw new AuthMissmatchPasswordException();
        }

        member.changePassword(passwordEncoder.encode(newPassword));
        return true;
    }

    @Transactional
    public boolean changeTempPassword(Long id, String newPassword) {
        Member member = memberRepository.findById(id).orElseThrow(()->new AuthNotFoundIdException());
        member.changePassword(passwordEncoder.encode(newPassword));
        return true;
    }

    @Transactional
    public boolean getUserId(String userId) {
        if(memberRepository.findByUserId(userId).isPresent()) {
            return false;
        }
        return true;
    }

    @Transactional
    public Member validMailAndName(String email, String name) {
        return  memberRepository.findByEmailAndName(email, name).orElseThrow(() -> new AuthMissmatchEmailAndNameException());

    }
}

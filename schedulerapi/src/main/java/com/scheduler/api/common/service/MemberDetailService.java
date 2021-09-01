package com.scheduler.api.common.service;

import com.scheduler.api.common.exception.AuthNotFoundIdException;
import com.scheduler.api.domain.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return memberRepository.findById(Long.valueOf(username)).orElseThrow(()->new AuthNotFoundIdException());
    }

}

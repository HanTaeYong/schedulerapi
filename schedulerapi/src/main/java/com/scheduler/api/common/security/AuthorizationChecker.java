package com.scheduler.api.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class AuthorizationChecker {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    String checkToken(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        if (token == null) {
            request.setAttribute("expired", "expired");
            return null;
        }

        if(!jwtTokenProvider.validateToken(token)) {
            request.setAttribute("expired", "expired");
            return null;
        }

        return token;
    }

    public boolean idCheck(HttpServletRequest request, Long id) {
        String token = checkToken(request);
        if (token == null) {
            return false;
        }

        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        if (!id.equals(userId)) {
            return false;
        }
        return true;
    }

}

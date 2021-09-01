package com.scheduler.api.common.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider { // JWT 토큰 생성 및 검증 모듈

    @Value("${spring.jwt.secret}")
    private String secretKey;

    public static final String AUTHORIZATION_HEADER = "x-api-token";

    private final long tokenValidInMiliSeconds = 1000L * 60 * 60 * 24;

    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + tokenValidInMiliSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER);
    }

    public boolean validateToken(String token) {
        try {
            Date now = new Date();
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

            Date expire = claims.getBody().getExpiration();
            if (expire.before(now)) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                log.error("token expire : expire({})/now({})", dateFormat.format(expire), dateFormat.format(now));
            }
            return !expire.before(now);

        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다. : {}({})", token.toString(), e.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다. : {}({})", token.toString(), e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다. : {}({})", token.toString(), e.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다. : {}({})", token.toString(), e.getMessage());
        }
        return false;

    }
}

package com.scheduler.api.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .antMatchers("/etc/hello").permitAll()
                    // 로그인 모두 허용
                    .antMatchers("/auth/login").permitAll()
                    // 아이디 찾기 모두 허용
                    .antMatchers("/auth/id").permitAll()
                    // 비밀번호 찾기 모두 허용
                    .antMatchers("/auth/password").permitAll()
                    // 회원가입 및 아이디 중복체크 모두 허용
                    .antMatchers("/member").permitAll()
                    // 회원정보, 수정, 비밀번호 변경 본인만 허용.
                    .antMatchers("/member/{id}/**").access("@authorizationChecker.idCheck(request, #id)")
                    // 본인 일정 조회 본인만 허용.
                    .antMatchers(HttpMethod.GET, "/agenda/{id}/**").access("@authorizationChecker.idCheck(request, #id)")
                    .anyRequest().authenticated()

                .and()
                    .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_LIST);
    };

    private static final String[] AUTH_LIST = {
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger/**",
            "/actuator/health",
            "/h2-console/**",
            "/favicon.ico",
            "/error"
    };
}

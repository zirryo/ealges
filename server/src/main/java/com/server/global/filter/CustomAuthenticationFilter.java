package com.server.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.global.error.exception.InvalidValueException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {

        if(!isPost(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);

        if (!StringUtils.hasLength(accountDto.getLoginId()) || !StringUtils.hasLength(accountDto.getPassword())) {
            throw new InvalidValueException("아이디 혹은 비밀번호가 비어있습니다.");
        }

        CustomAuthenticationToken token = new CustomAuthenticationToken(
                accountDto.getLoginId(), accountDto.getPassword());

        return getAuthenticationManager().authenticate(token);

    }

    private boolean isPost(HttpServletRequest request) {
        return "POST".equals(request.getMethod());
    }

    @Data
    public static class AccountDto {
        private String loginId;
        private String password;
    }
}

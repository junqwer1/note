package com.final_project.member.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.final_project.global.rests.JSONData;
import com.final_project.member.Member;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/*@Component
@RequiredArgsConstructor
public class LoginFilter extends GenericFilterBean {

    private final RestTemplate restTemplate;
    private final ObjectMapper om;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String token = getToken(httpRequest);

        if (StringUtils.hasText(token)) {
            loginProcess(token);
        }

        chain.doFilter(request, response);
    }

    private void loginProcess(String token) {

        if (!StringUtils.hasText(token)) {
            return; // 토큰이 없는 일반 요청인 경우는 처리 X
        }

        *//**
         * 1. 토큰이 있으면, member-service 인스턴스에서 회원 정보 조회
         * 2. 로그인 처리
         *//*
        try {
            // member-service의 URL을 설정 파일(application.yml)에서 관리하는 것이 좋습니다.
            String memberServiceUrl = "http://localhost:8081/api/v1/member"; // member-service의 회원정보 조회 API

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            HttpEntity<Void> request = new HttpEntity<>(headers);

            ResponseEntity<JSONData> response = restTemplate.exchange(memberServiceUrl, HttpMethod.GET, request, JSONData.class);
            JSONData jsonData = response.getBody();

            if (response.getStatusCode().is2xxSuccessful() && jsonData != null && jsonData.isSuccess()) {
                String json = om.writeValueAsString(jsonData.getData());

                Member member = om.readValue(json, Member.class);

                Authentication auth = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // 토큰 검증 실패 시 아무것도 하지 않고 넘어감 (미로그인 상태로 처리)
            e.printStackTrace();
        }
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}*/

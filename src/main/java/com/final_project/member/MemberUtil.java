package com.final_project.member;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class MemberUtil {

    // 로그인 상태 여부 체크
    public boolean isLogin() {

        return getMember() != null;
    }

    // 로그인 회원 정보 조회
    public Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Member) {
//            System.out.println("member : " + authentication.getPrincipal());
            return (Member) authentication.getPrincipal();
        }

        return null;
    }

    /*public String getMemberId() {
        return isLogin() ? getMember().getMemberId() : null;
    }*/
}

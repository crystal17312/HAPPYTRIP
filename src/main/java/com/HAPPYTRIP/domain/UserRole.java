package com.HAPPYTRIP.domain;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"), //관리자 역할
    USER("ROLE_USER"); //사용자 역할

    //역할 이름을 저장하는 필드
    UserRole(String value) {
        this.value = value;
    }

    //역할 이름을 초기화하는 생성자
    private String value;
}
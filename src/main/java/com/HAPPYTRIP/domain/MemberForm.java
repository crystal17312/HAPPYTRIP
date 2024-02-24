package com.HAPPYTRIP.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberForm {

    @NotBlank(message = "아이디를 입력하세요.")
    private String id;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String password;

    @NotBlank(message = "비밀번호 중복 확인을 하세요.")
    private String checkpassword;

    @NotBlank(message = "이름을 입력하세요.")
    private String name;

    @NotBlank(message = "전화번호를 입력하세요.")
    private String phone;

    @NotBlank(message = "생년월일을 입력하세요.")
    private LocalDateTime birthday;
}

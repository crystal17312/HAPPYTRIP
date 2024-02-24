package com.HAPPYTRIP.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank(message = "사용자 ID는 필수 항목입니다.")
    private String memberId;

    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    private String password;

}

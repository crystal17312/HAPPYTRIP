package com.HAPPYTRIP.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class LoginController {

    //로그인
    @GetMapping("/login")
    public String LoginForm() {
        return "/login";
    }


}
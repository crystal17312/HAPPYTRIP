package com.HAPPYTRIP.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    @GetMapping("")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }
}

package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public String adminMembers(Model model) {
        List<Member> memberList = this.memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "adminMembers";
    }

    @GetMapping("/booking")
    public String adminBooking() {
        return "adminBooking";
    }

    @GetMapping("/notice")
    public String adminNotice() {
        return "adminNotice";
    }

    @GetMapping("/board")
    public String adminBoard() {
        return "adminBoard";
    }

}

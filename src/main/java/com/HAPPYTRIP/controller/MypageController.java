package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //마이페이지
    @GetMapping("")
    public String mypage() {
        return "mypage";
    }

    @GetMapping("/update")
    public String updateForm(Model model, Principal principal) {

        String username = principal.getName();
        // 사용자 정보를 이용하여 member 객체 생성 혹은 조회
        Optional<Member> optionalMember = memberService.findByUserId(username);
        optionalMember.ifPresent(member -> model.addAttribute("member", member));
        return "/updateForm";
    }

    @PostMapping("/update")
    public String updatePassword(@RequestParam("name") String name,
                                 @RequestParam("phone") String phone,
                                 @RequestParam("password") String password,
                                 Principal principal) {
        String username = principal.getName();
        Optional<Member> optionalMember = memberService.findByUserId(username);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.setName(name);
            member.setPhone(phone);
            member.setPassword(password);
            memberService.update(member);
        }

        return "/mypage";
    }

    @PostMapping("/delete")
    public String deleteMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        memberService.deleteByUserId(userId);
        SecurityContextHolder.clearContext();

        return "redirect:/member/logout";
    }
}

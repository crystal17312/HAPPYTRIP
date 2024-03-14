package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class MypageController {

    private final MemberService memberService;

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

    //회원정보 수정
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

    //회원 탈퇴
    @GetMapping("/deleteCheck")
    public String deleteCheck() {
        return "/deleteCheck";
    }
    @PostMapping("/delete")
    public String deleteMember(String password, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        // 비밀번호 확인
        if (memberService.deleteCheck(userId, password)) {
            // 비밀번호가 일치하는 경우 회원 삭제
            memberService.deleteByUserId(userId);
            return "redirect:/member/logout";
        } else {
            // 비밀번호가 일치하지 않는 경우 에러 플래그 설정
            model.addAttribute("error", true);
            return "/deleteCheck";
        }
    }
}

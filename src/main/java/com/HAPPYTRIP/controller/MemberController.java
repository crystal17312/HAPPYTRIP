package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.MemberForm;
import com.HAPPYTRIP.domain.UserRole;
import com.HAPPYTRIP.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String JoinForm(MemberForm memberForm) {
        return "join";
    }


    @PostMapping("/join")
    public String join(@Valid MemberForm memberForm, BindingResult bindingResult) {

        //if 오류 발생
        if (bindingResult.hasErrors()) {
            return "join";
        }

        //if 비밀번호, 비밀번호 확인이 일치하지 않음
//        if (!memberForm.getPassword().equals(memberForm.getCheckpassword())) {
//
//            //errorCode는 "passwordInCorrect"로 정의
//            bindingResult.rejectValue("checkpassword", "passwordInCorrect", "패스워드가 일치하지 않습니다.");
//            return "join";
//        }

        try {
            System.out.println(memberForm.getId());
            memberService.create(memberForm.getId(), memberForm.getPassword(), memberForm.getName(),memberForm.getPhone(), memberForm.getBirthday(), UserRole.USER);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("joinFailed", "이미 등록된 사용자입니다.");
            return "join";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("joinFailed", e.getMessage());
            return "join";
        }

        return "redirect:/member/login";
    }
}

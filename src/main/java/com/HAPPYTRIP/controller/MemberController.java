package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.dto.MemberForm;
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

    //의존성 주입 @autowired 생략
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

        try {
            System.out.println(memberForm.getId());
            memberService.create(memberForm.getId(), memberForm.getPassword(), memberForm.getName(),
                    memberForm.getPhone(), memberForm.getBirthday(), UserRole.USER);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace(); //RuntimeException 으로 뭔가 잘못된 데이터가 바인딩 되었을때 발생하는 에러 SQL 문이 잘못되었거나 Data 가 잘못되었을경우
            bindingResult.reject("joinFailed", "이미 등록된 사용자입니다.");
            return "join";
        } catch (Exception e) {
            e.printStackTrace(); //예외발생시 호출스택에 있던 메서드 예외 결과를 출력
            bindingResult.reject("joinFailed", e.getMessage());
            return "join";
        }

        return "redirect:/member/login";
    }
}

package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    //회원생성
    public Member create(String userId, String password, String name, String phone, LocalDateTime birthday) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(passwordEncoder.encode(password));
        member.setName(name);
        member.setPhone(phone);
        member.setBirthday(birthday);
        this.memberRepository.save(member);
        return member;
    }
}

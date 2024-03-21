package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.domain.Notice;
import com.HAPPYTRIP.domain.UserRole;
import com.HAPPYTRIP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {


    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    //조회
    public List<Member> getList() {
        return memberRepository.findAll();
    }

    //추가
    public Member create(String userId, String password, String name, String phone, String birthday, UserRole role) {
        Member member = new Member();
        member.setUserId(userId);
        member.setPassword(passwordEncoder.encode(password));
        member.setName(name);
        member.setPhone(phone);
        member.setBirthday(birthday);
        member.setRole(role);
        memberRepository.save(member);
        return member;
    }

    //수정
        public Member update(Member member) {
            Member persistence = memberRepository.findByUserId(member.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

            persistence.setName(member.getName());
            persistence.setPhone(member.getPhone());

            if (member.getPassword() != null && !member.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(member.getPassword());
                persistence.setPassword(encodedPassword);
            }

            return memberRepository.save(persistence);
        }

        public Optional<Member> findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    //삭제
    public void deleteByUserId(String userId) {
        memberRepository.deleteByUserId(userId);
    }

    public boolean deleteCheck(String userId, String password) {
        Optional<Member> optionalMember = memberRepository.findByUserId(userId);
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            return passwordEncoder.matches(password, member.getPassword());
        } else {
            return false;
        }
    }
}
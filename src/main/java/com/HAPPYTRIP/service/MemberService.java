package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.domain.UserRole;
import com.HAPPYTRIP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MemberService {


    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    //회원생성
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

    //회원정보 수정
        @Transactional
        public Member update(Member member) {
            Member persistence = memberRepository.findByUserId(member.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

            // 전달받은 회원 객체의 정보로 데이터베이스에 저장된 회원 정보를 업데이트합니다.
            persistence.setName(member.getName());
            persistence.setPhone(member.getPhone());

            // 새로운 비밀번호가 전달되었다면, 비밀번호를 암호화하여 업데이트합니다.
            if (member.getPassword() != null && !member.getPassword().isEmpty()) {
                String encodedPassword = passwordEncoder.encode(member.getPassword());
                persistence.setPassword(encodedPassword);
            }

            // 업데이트된 회원 정보를 저장하고 반환합니다.
            return memberRepository.save(persistence);
        }

        @Transactional
        public Optional<Member> findByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }

    @Transactional
    public Member getMember(String name) {
            return memberRepository.findByName(name);
    }

    //회원탈퇴
    @Transactional
    public void deleteByUserId(String userId) {
        memberRepository.deleteByUserId(userId);
    }

    @Transactional
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
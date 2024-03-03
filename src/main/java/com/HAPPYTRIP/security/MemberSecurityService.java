package com.HAPPYTRIP.security;

import com.HAPPYTRIP.domain.Admin;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.repository.AdminRepository;
import com.HAPPYTRIP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByAdmin(username);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            // 관리자인 경우 ADMIN 권한을 부여
            return new User(admin.getAdmin(), admin.getPassword(), List.of(new SimpleGrantedAuthority("ADMIN")));
        }

        // 멤버 테이블에서 조회
        Optional<Member> memberOptional = memberRepository.findByUserId(username);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            // 일반 사용자인 경우 USER 권한을 부여
            return new User(member.getUserId(), member.getPassword(), List.of(new SimpleGrantedAuthority("USER")));
        }

        throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username);
    }

}

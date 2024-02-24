package com.HAPPYTRIP.security;

import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //if 사용자명에 해당하는 데이터가 없는 경우 -> UsernameNotFoundException을 발생
    //if 사용자명이 admin인 경우 ADMIN 권한, 그 외는 USER 권환을 부여
    //User 객체를 생성해 반환 (스프링 스큐리티에서 사용하며 User 생성자에는 사용자명, 비밀번호, 권한 리스트가 전달)
    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Optional<Member> userOptional = memberRepository.findByUserId(memberId);
        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        Member member = userOptional.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("ADMIN".equals(memberId)){
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.toString()));
        }else{
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.toString()));
        }
        return new User(siteUser.getUserId(),siteUser.getPassword(),authorities);
    }

}

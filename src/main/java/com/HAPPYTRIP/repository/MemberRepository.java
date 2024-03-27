package com.HAPPYTRIP.repository;

import com.HAPPYTRIP.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);
    Optional<Member> findByName(String name);
    void deleteByUserId(String userId);




}

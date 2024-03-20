package com.HAPPYTRIP.repository;

import com.HAPPYTRIP.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAll();

    Optional<Notice> findById(Long id);

}

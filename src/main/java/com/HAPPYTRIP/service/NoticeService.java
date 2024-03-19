package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Notice;
import com.HAPPYTRIP.repository.NoticeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getList() {
        return this.noticeRepository.findAll();
    }

    public Notice getNotice(Long id) {
        Optional<Notice> notice = this.noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new RuntimeException("Notice not found");
        }
    }

    //추가
    public void create(String title, String content) {
        Notice n = new Notice();
        n.setTitle(title);
        n.setContent(content);
        n.setDate(LocalDateTime.now());
        this.noticeRepository.save(n);
    }

    //수정
    public void update(Long id, String title, String content) {
        Optional<Notice> optionalNotice = this.noticeRepository.findById(id);
        if (optionalNotice.isPresent()) {
            Notice n = optionalNotice.get();
            n.setTitle(title);
            n.setContent(content);
            this.noticeRepository.save(n);
        } else {
            throw new EntityNotFoundException("공지를 찾을 수 없습니다.");
        }
    }

    //삭제
    public void delete(Notice notice) {
        this.noticeRepository.delete(notice);
    }
}

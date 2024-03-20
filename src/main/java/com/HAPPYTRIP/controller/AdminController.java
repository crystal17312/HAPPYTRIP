package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.domain.Notice;
import com.HAPPYTRIP.repository.BoardRepository;
import com.HAPPYTRIP.repository.MemberRepository;
import com.HAPPYTRIP.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final NoticeService noticeService;

    @GetMapping("/members")
    public String adminMembers(Model model) {
        List<Member> memberList = this.memberRepository.findAll();
        model.addAttribute("memberList", memberList);
        return "/admin/adminMembers";
    }

    @GetMapping("/booking")
    public String adminBooking() {
        return "/admin/adminBooking";
    }

    @GetMapping("/notice/list")
    public String list(Model model) {
        List<Notice> noticeList = this.noticeService.getList();
        model.addAttribute("noticeList", noticeList);
        return "/admin/adminNoticeList";
    }

    @GetMapping("/notice/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Notice notice = this.noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "/admin/adminNoticeDetail";
    }

    //추가
    @GetMapping("/notice/create")
    public String noticeCreate() {
        return "/admin/adminNoticeForm";
    }

    @PostMapping("/notice/create")
    public String noticeCreate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        noticeService.create(title, content);
        return "redirect:/admin/notice/list";
    }

    //수정
    @GetMapping("/notice/update/{id}")
    public String noticeUpdate(Model model, @PathVariable("id") Long id) {
        Notice notice = this.noticeService.getNotice(id);
        notice.setTitle(notice.getTitle());
        notice.setContent(notice.getContent());
        return "/admin/adminNoticeForm";
    }

    @PostMapping("/notice/update/{id}")
    public String noticeUpdate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content, @PathVariable("id") Long id) {
        noticeService.update(id, title, content);
        return String.format("redirect:/admin/notice/list", id);
    }

    //삭제
    @GetMapping("/notice/delete/{id}")
    public String noticeDelete(@PathVariable("id") Long id) {
        Notice notice = this.noticeService.getNotice(id);
        this.noticeService.delete(notice);
        return "redirect:/admin/notice/list";
    }
    @GetMapping("/board")
    public String adminBoard(Model model) {
        List<Board> boardList = this.boardRepository.findAll();
        model.addAttribute("boardList", boardList);
        return "/admin/adminBoard";
    }

}

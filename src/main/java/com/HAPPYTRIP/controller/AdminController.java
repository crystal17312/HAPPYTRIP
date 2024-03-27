package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.domain.Notice;
import com.HAPPYTRIP.service.BoardService;
import com.HAPPYTRIP.service.MemberService;
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

    private final BoardService boardService;
    private final NoticeService noticeService;
    private final MemberService memberService;

    //회원 관리
    @GetMapping("/members")
    public String adminMembers(Model model) {
        List<Member> memberList = memberService.getList();
        model.addAttribute("memberList", memberList);
        return "/admin/adminMembers";
    }

    //예약 관리
    @GetMapping("/booking")
    public String adminBooking() {
        return "/admin/adminBooking";
    }

    //게시판 관리
    @GetMapping("/board")
    public String adminBoard(Model model) {
        List<Board> boardList = boardService.getList();
        model.addAttribute("boardList", boardList);
        return "/admin/adminBoard";
    }

    //공지 관리
    @GetMapping("/notice/list")
    public String list(Model model) {
        List<Notice> noticeList = noticeService.getList();
        model.addAttribute("noticeList", noticeList);
        return "/admin/adminNoticeList";
    }

    @GetMapping("/notice/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Notice notice = noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "/admin/adminNoticeDetail";
    }

    //공지 추가
    @GetMapping("/notice/create")
    public String noticeCreate() {
        return "/admin/adminNoticeForm";
    }

    @PostMapping("/notice/create")
    public String noticeCreate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        noticeService.create(title, content);
        return "redirect:/admin/notice/list";
    }

    //공지 수정
    @GetMapping("/notice/update/{id}")
    public String noticeUpdate(@PathVariable("id") Long id) {
        Notice notice = noticeService.getNotice(id);
        notice.setTitle(notice.getTitle());
        notice.setContent(notice.getContent());
        return "/admin/adminNoticeForm";
    }

    @PostMapping("/notice/update/{id}")
    public String noticeUpdate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content, @PathVariable("id") Long id) {
        noticeService.update(id, title, content);
        return String.format("redirect:/admin/notice/list", id);
    }

    //공지 삭제
    @GetMapping("/notice/delete/{id}")
    public String noticeDelete(@PathVariable("id") Long id) {
        Notice notice = noticeService.getNotice(id);
        noticeService.delete(notice);
        return "redirect:/admin/notice/list";
    }

    // 삭제 버튼
    @PostMapping("/deleteMember/{id}")
    public String deleteMember(@PathVariable("id") String userId) {
        memberService.deleteByUserId(userId);
        return "redirect:/admin/members";
    }

    @PostMapping("/deleteBoard/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteById(id);
        return "redirect:/admin/board";
    }

    @PostMapping("/deleteNotice/{id}")
    public String deleteNotice(@PathVariable("id") Long id) {
        noticeService.deleteById(id);
        return "redirect:/admin/notice/list";
    }

}

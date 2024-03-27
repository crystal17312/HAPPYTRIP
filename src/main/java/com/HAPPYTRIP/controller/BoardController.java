package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.service.BoardService;
import com.HAPPYTRIP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;
    private final MemberService memberService;

    //조회
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = boardservice.getList();
        model.addAttribute("boardList", boardList);
        return "boardList";
    }


    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Board board = boardservice.getBoard(id);
        model.addAttribute("board", board);
        return "boardDetail";
    }

    //추가
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String boardCreate() {
        return "boardForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String boardCreate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content, Principal principal) {
        String username = principal.getName();
        Member member=memberService.getMember(username);
        boardservice.create(title, content, member);
        return "redirect:/board/list";
    }

    //수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id) {
        return "boardForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String boardUpdate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content, @PathVariable("id") Long id) {
        boardservice.update(id, title, content);
        return "redirect:/board/list";
    }

    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String boardDelete(@PathVariable("id") Long id) {
        Board board = boardservice.getBoard(id);
        boardservice.delete(board);
        return "redirect:/home";
    }
}

package com.HAPPYTRIP.controller;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.service.BoardService;
import com.HAPPYTRIP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardservice;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = this.boardservice.getList();
        model.addAttribute("boardList", boardList);
        return "boardList";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Board board = this.boardservice.getBoard(id);
        model.addAttribute("board", board);
        return "boardDetail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String boardCreate() {
        return "boardForm";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String boardCreate(@RequestParam(value = "title") String title, @RequestParam(value = "content") String content) {
        this.boardservice.create(title, content);
        return "redirect:/board/list";
    }
}

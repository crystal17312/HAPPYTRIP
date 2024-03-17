package com.HAPPYTRIP.controller;


import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.service.BoardService;
import com.HAPPYTRIP.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @RequestParam(value = "content") String content) {
        Board board = this.boardService.getBoard(id);
        this.commentService.create(board, content);
        return String.format("redirect:/board/detail/%s", id);
    }
}

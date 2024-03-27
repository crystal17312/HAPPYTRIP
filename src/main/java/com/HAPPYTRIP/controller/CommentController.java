package com.HAPPYTRIP.controller;


import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Comment;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.service.BoardService;
import com.HAPPYTRIP.service.CommentService;
import com.HAPPYTRIP.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;


@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberService memberService;

    //추가
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(@PathVariable("id") Long id, @RequestParam(value = "content") String content, Principal principal) {
        String username = principal.getName();
        Member member=memberService.getMember(username);
        Board board = this.boardService.getBoard(id);
        this.commentService.create(board, content, member);
        return String.format("redirect:/board/detail/%s", id);
    }

    //수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String commentUpdate( @PathVariable("id") Long id) {

        return "commentForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String commentUpdate(@RequestParam(value = "content") String content, @PathVariable("id") Long id) {
        Comment comment=commentService.getComment(id);
        commentService.update(id,content);
        return String.format("redirect:/board/detail/%s", comment.getBoard().getId().toString());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String commentDelete(@PathVariable("id") Long id) {
        Comment comment = this.commentService.getComment(id);

        this.commentService.delete(comment);
        return String.format("redirect:/board/detail/%s", comment.getBoard().getId());
    }
}

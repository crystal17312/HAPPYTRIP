package com.HAPPYTRIP.controller;


import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Comment;
import com.HAPPYTRIP.service.BoardService;
import com.HAPPYTRIP.service.CommentService;
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

    //추가
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(@PathVariable("id") Long id, @RequestParam(value = "content") String content, Principal principal) {
        String username = principal.getName();
        Board board = boardService.getBoard(id);
        commentService.create(board, content, username);
        return String.format("redirect:/board/detail/%s", id);
    }

    //수정
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/update/{id}")
    public String commentUpdate(@PathVariable("id") Long id, Principal principal) {
        Comment comment = this.commentService.getComment(id);
        if(!comment.getAuthor().equals(principal.getName())) {
            throw new
                    ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        comment.setContent(comment.getContent());
        return "commentForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public String commentUpdate(@RequestParam(value = "content") String content, @PathVariable("id") Long id, Principal principal) {
        Comment comment = this.commentService.getComment(id);
        if (!comment.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        String username = principal.getName();
        this.commentService.update(id,content, username);
        return String.format("redirect:/board/detail/%s", id);
    }


    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String commentDelete(Principal principal, @PathVariable("id") Long id) {
        Comment comment = commentService.getComment(id);
        if (!comment.getAuthor().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        commentService.delete(comment);
        return String.format("redirect:/board/detail/%s", comment.getBoard().getId());
    }
}

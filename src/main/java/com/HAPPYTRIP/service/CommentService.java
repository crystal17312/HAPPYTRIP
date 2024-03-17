package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Comment;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment create(Board board, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setDate(LocalDateTime.now());
        comment.setBoard(board);
        this.commentRepository.save(comment);
        return comment;
    }
}

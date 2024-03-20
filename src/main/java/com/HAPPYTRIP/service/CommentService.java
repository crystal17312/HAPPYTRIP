package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Comment;
import com.HAPPYTRIP.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment getComment(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new RuntimeException("comment not found");
        }
    }

    //추가
    public Comment create(Board board, String content, String author) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setDate(LocalDateTime.now());
        comment.setBoard(board);
        comment.setAuthor(author);
        this.commentRepository.save(comment);
        return comment;
    }

    //수정
    public void update(Long id, String content, String author) {
        Optional<Comment> optionalComment = this.commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            Comment c = optionalComment.get();
            c.setContent(content);
            c.setAuthor(author);
            this.commentRepository.save(c);
        } else {
            throw new EntityNotFoundException("댓글을 찾을 수 없습니다.");
        }
    }

    //삭제
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }
}

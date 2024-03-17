package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> getList() {
        return this.boardRepository.findAll();
    }
    public Board getBoard(Long id) {
        Optional<Board> question = this.boardRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("board not found");
        }
    }

    public void create(String title, String content, String author) {
        Board b = new Board();
        b.setTitle(title);
        b.setContent(content);
        b.setDate(LocalDateTime.now());
        b.setAuthor(author);
        this.boardRepository.save(b);
    }

}

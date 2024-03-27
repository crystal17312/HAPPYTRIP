package com.HAPPYTRIP.service;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Member;
import com.HAPPYTRIP.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    //조회
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    public Board getBoard(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        if (board.isPresent()) {
            return board.get();
        } else {
            throw new RuntimeException("board not found");
        }
    }


    //추가
    public void create(String title, String content, Member member) {
        Board b = new Board();
        b.setTitle(title);
        b.setContent(content);
        b.setMemberId(member);
        this.boardRepository.save(b);
    }

    //수정
    public void update(Long id, String title, String content, Member member) {
        Optional<Board> optionalBoard = this.boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board b = optionalBoard.get();
            b.setTitle(title);
            b.setContent(content);
            b.setMemberId(member);
            this.boardRepository.save(b);
        } else {
            throw new EntityNotFoundException("게시물을 찾을 수 없습니다.");
        }
    }

    //삭제
    public void delete(Board board) {
        boardRepository.delete(board);
    }
  
}

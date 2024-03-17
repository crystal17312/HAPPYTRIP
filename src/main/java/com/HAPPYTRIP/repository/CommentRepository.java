package com.HAPPYTRIP.repository;

import com.HAPPYTRIP.domain.Board;
import com.HAPPYTRIP.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}

package com.sparta.testjwt.repository;

import com.sparta.testjwt.entity.Board;
import com.sparta.testjwt.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardOrderByCreatedAtDesc(Board board);
}

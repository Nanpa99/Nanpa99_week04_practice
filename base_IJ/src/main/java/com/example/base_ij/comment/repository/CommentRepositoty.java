package com.example.base_ij.comment.repository;


import com.example.base_ij.board.entity.Board;
import com.example.base_ij.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepositoty extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoard(Board board);
}

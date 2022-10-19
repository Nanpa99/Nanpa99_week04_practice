package com.example.base_ij.comment.repository;

import com.example.base_ij.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepositoty extends JpaRepository<Comment, Long> {


}

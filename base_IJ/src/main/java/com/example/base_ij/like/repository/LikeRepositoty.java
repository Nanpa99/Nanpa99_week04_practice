package com.example.base_ij.like.repository;

import com.example.base_ij.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LikeRepositoty extends JpaRepository<Likes, Long> {

//    Optional<Likes> findByBoardIdAndAndLikeUserId(Long boardId,Long likeUserId);

    Optional<Likes> findByBoardId(Long boardId);
}


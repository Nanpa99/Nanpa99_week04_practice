package com.example.base_ij.likes.repository;

import com.example.base_ij.board.entity.Board;
import com.example.base_ij.likes.entity.Likes;
import com.example.base_ij.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepositoty extends JpaRepository<Likes, Long> {

    Optional<Likes> findByBoardAndMember(Board board, Member member);
}

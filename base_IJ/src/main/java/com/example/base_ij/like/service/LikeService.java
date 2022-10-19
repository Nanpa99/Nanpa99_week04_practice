package com.example.base_ij.like.service;


import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.repository.BoardRepository;
import com.example.base_ij.like.dto.request.LikeRequestDto;
import com.example.base_ij.like.entity.Likes;
import com.example.base_ij.like.repository.LikeRepositoty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class LikeService {

// like전용

    private final LikeRepositoty likeRepositoty;

    private final BoardRepository boardRepository;

    @Transactional
    public boolean love(LikeRequestDto likeRequestDto) {
        // 게시판 전용 id
        Long boardId = likeRequestDto.getBoardId();
//        Long userId = 1L;
//        Optional<Likes> likes = likeRepositoty.findByBoardIdAndAndLikeUserId(boardId,userId);

        // 저장되여있는 특정 게시판의 좋아요 가져오기. // 유저 게시글당 1개씩이면 뒤에 userId 해보면될듯 // 현재 게시글당 한개.
        Optional<Likes> likes = likeRepositoty.findByBoardId(boardId);

        // 값이 null이 아니면 (좋아요가 눌려져있는경우  삭제)
        if (likes.isPresent()) {
            likeRepositoty.deleteById(likes.get().getId());

            return false;
        } else {
            Likes likes1 = new Likes(boardId);
            likeRepositoty.save(likes1);

            return true;

        }

    }
}

// 게시글 id 가져와서 그 id로 특정 게시글 추출  - > 추출한 게시글 json에 like 부분 update //

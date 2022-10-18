package com.example.base_ij.like.service;


import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.repository.BoardRepository;
import com.example.base_ij.like.dto.request.LikeRequestDto;
import com.example.base_ij.like.entity.Likes;
import com.example.base_ij.like.repository.LikeRepositoty;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class LikeService {

// like전용

    private final LikeRepositoty likeRepositoty;

// board전용
    private final BoardRepository boardRepository;


    @Transactional
    // 게시글 id (입장 : 유저 본인)
    public boolean userLike(Long id, LikeRequestDto likeRequestDto){

        Board board = boardRepository.findById(id).orElse(null);

        Likes likes = Likes.createLike(likeRequestDto,likes);


    }



}

// 게시글 id 가져와서 그 id로 특정 게시글 추출  - > 추출한 게시글 json에 like 부분 update //

package com.example.base_ij.like.controller;


import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.repository.BoardRepository;
import com.example.base_ij.like.dto.request.LikeRequestDto;

import com.example.base_ij.like.entity.Likes;
import com.example.base_ij.like.repository.LikeRepositoty;
import com.example.base_ij.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // 뷰 vs json.

@RequiredArgsConstructor
public class LikeController {


    private final LikeService likeService;

    private final LikeRepositoty likeRepositoty;

    private final BoardRepository boardRepository;

    @PostMapping("/like")
    public boolean getlove(@RequestParam Long boardId,@RequestBody LikeRequestDto likeRequestDto){

        // addlike 사용할려고 가져옴.
        Board board = boardRepository.findById(boardId).orElse(null);
        // 일단 세이브만.

        return likeService.love(likeRequestDto);
    }
}

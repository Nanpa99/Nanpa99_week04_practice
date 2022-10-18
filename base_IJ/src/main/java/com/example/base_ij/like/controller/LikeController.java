package com.example.base_ij.like.controller;


import com.example.base_ij.like.dto.request.LikeRequestDto;

import com.example.base_ij.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // 뷰 vs json.

@RequiredArgsConstructor
public class LikeController {


    private final LikeService likeService;

//    private final LikeRepositoty likeRepositoty;
//putmapping
    @PostMapping("/like/{id}")
    public boolean testLike(@PathVariable Long id, @RequestBody LikeRequestDto dto){

        return likeService.userLike(id,dto);
    }


}

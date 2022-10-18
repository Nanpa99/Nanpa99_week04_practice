package com.example.base_ij.like.dto.response;


import com.example.base_ij.like.entity.Likes;
import lombok.Getter;

@Getter
public class LikeResponseDto {

    private boolean like_choice;

    public LikeResponseDto(Likes likes){

        this.like_choice = likes.isLike_choice();
    }

//    private String likeA;
//
//    public LikeResponseDto(Likes likes) {
//        this.likeA = likes.getLikeA();
//    }

//        public LikeResponseDto(String likeA) {
//        this.likeA = likeA;
//    }
}

package com.example.base_ij.comment.dto.response;

import com.example.base_ij.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private String nickname;

    private String comments;

    public CommentResponseDto(String nickname, String comments) {
        this.nickname = nickname;
        this.comments = comments;
    }
    public CommentResponseDto(Comment comment){
        this.comments = comment.getComments();
        this.nickname = comment.getNickname();
    }
}

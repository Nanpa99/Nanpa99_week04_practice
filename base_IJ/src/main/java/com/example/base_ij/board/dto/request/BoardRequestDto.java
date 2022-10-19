package com.example.base_ij.board.dto.request;

import com.example.base_ij.board.entity.Board;
import com.example.base_ij.like.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@ToString
public class BoardRequestDto {



    private String title;
    private String content;

    private String nickname;



    public Board toEntity() {
        return new Board(title,content,nickname);
    }
}

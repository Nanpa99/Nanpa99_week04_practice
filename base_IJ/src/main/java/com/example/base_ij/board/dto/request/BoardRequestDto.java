package com.example.base_ij.board.dto.request;

import com.example.base_ij.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.ToString;

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

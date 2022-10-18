package com.sparta.testjwt.dto.response;

import com.sparta.testjwt.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private String username;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUsername();
    }
}

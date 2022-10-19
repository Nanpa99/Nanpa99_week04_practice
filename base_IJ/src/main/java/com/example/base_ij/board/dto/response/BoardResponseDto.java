package com.example.base_ij.board.dto.response;

import com.example.base_ij.board.entity.Board;
import com.example.base_ij.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private String nickname;
    private List<Comment> comments;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.nickname = board.getNickname();
        this.comments = board.getComments();
    }

}

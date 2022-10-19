package com.sparta.testjwt.dto.response;

import com.sparta.testjwt.entity.Board;
import com.sparta.testjwt.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private String username;
    private List<Comment> commentList;

    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.username = board.getUsername();
        this.commentList = board.getCommentList();
    }
}

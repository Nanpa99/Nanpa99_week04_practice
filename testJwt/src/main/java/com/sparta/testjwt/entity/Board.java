package com.sparta.testjwt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.testjwt.dto.request.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Board extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String username;

    @JsonIgnore // 순환참조 예방용. dto에 더 신경쓰길 권장
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
    @OneToMany(mappedBy = "board")
    private final List<Comment> commentList = new ArrayList<>();
    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = requestDto.getUsername();
    }

    public void addComment(Comment comment){
        comment.setBoard(this);
        this.commentList.add(comment);
    }
}

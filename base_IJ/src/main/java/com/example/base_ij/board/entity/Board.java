package com.example.base_ij.board.entity;

import com.example.base_ij.board.dto.request.BoardRequestDto;
import com.example.base_ij.comment.entity.Comment;
import com.example.base_ij.jwt.Timestamped;
import com.example.base_ij.members.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder

public class Board extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comment> comments = new ArrayList<>();

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.nickname = requestDto.getNickname();
    }

    public void boardUpdate(BoardRequestDto requestDto){
        this.title = requestDto.getTitle() != null ? requestDto.getTitle() : this.title;
        this.content = requestDto.getContent() != null ? requestDto.getContent() : this.title;
        this.nickname = requestDto.getNickname() != null ? requestDto.getNickname() : this.title;
    }

    public void addComment(Comment comment){
        comment.setBoard(this);
        this.comments.add(comment);
    }
    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }
}

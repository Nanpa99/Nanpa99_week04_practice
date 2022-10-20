package com.example.base_ij.likes.entity;

import com.example.base_ij.board.entity.Board;
import com.example.base_ij.comment.dto.request.CommentRequestDto;
import com.example.base_ij.jwt.Timestamped;
import com.example.base_ij.members.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder

public class Likes extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public Likes(Member member, Board board){
        member.addLikes(this);
        board.addLikes(this);
    }

    public Likes() {

    }

}

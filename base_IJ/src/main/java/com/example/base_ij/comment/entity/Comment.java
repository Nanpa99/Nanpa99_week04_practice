package com.example.base_ij.comment.entity;


import com.example.base_ij.board.entity.Board;
import com.example.base_ij.comment.dto.request.CommentRequestDto;
import com.example.base_ij.jwt.Timestamped;
import com.example.base_ij.members.entity.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
@Builder
@AllArgsConstructor

public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Column(nullable = false)
    private String content;

    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }

    public boolean validateMember(Member member) {
        return !this.member.equals(member);
    }
}

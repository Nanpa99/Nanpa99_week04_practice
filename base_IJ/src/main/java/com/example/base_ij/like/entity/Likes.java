package com.example.base_ij.like.entity;

import com.example.base_ij.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Setter
public class Likes {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column
    private Long boardId;

    @Column
    private Long likeUserId;

    // 테스트 연관관계

    @ManyToOne
    @JoinColumn(name = "board_get_id")
    private Board board;


    public Likes(Long boardId, Long likeUserId) {
        this.boardId = boardId;
        this.likeUserId = likeUserId;

    }

    public Likes(Long boardId) {
        this.boardId = boardId;

    }




}


package com.sparta.testjwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "user")
    private final List<Board> boardList = new ArrayList<>();


    public User (String username, String password){
        this.username = username;
        this.password = password;
    }

    public void addBoard(Board board){
        board.setUser(this);
        this.boardList.add(board);
    }
}

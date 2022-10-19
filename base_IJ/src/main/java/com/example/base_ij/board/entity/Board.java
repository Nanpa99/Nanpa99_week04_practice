package com.example.base_ij.board.entity;

//import com.example.base_ij.like.entity.Likes;
import com.example.base_ij.like.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity//db가 인식하게 만든다 (해당클래스로 테이블을 만든다)
@AllArgsConstructor//생성자
@NoArgsConstructor//생성자에 변수가 없을때 값
@ToString
@Getter
public class Board extends Timestemped{

    @Id//대표값지정
    @GeneratedValue(strategy = GenerationType.IDENTITY/*db가 자동으로 만들어줌)*/)//자동 생성
    private Long id;

    @Column//db가 인식하게
    private String title;

    @Column
    private String content;

    @Column
    private String nickname;

//     total_like // -> cnt  //

    @OneToMany(mappedBy = "board")
    private List<Likes> likes_lst = new ArrayList<>();



    public void patch(Board board) {
        if (board.title != null)
            this.title = board.title;
        if (board.content != null)
            this.content = board.content;
    }

    public Board(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }
    public void addLike(Likes likes){
        likes.setBoard(this);
        this.likes_lst.add(likes);


    }
}

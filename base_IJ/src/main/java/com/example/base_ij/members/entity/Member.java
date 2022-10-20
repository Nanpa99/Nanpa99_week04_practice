package com.example.base_ij.members.entity;

import com.example.base_ij.likes.entity.Likes;
import com.example.base_ij.members.dto.request.MemberRequestDto;
import com.example.base_ij.jwt.Timestamped;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @JsonIgnore
    private String passwordConfirm;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Likes> likesList = new ArrayList<>();

    public Member(MemberRequestDto memberRequestDto) {
        this.nickname = memberRequestDto.getNickname();
        this.password = memberRequestDto.getPassword();
        this.passwordConfirm = memberRequestDto.getPasswordConfirm();
        this.email = memberRequestDto.getEmail();
    }

    public void addLikes(Likes likes){
        likes.setMember(this);
        this.likesList.add(likes);
    }

}

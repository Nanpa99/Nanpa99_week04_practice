package com.example.base_ij.members.entity;

import com.example.base_ij.members.dto.request.MemberRequestDto;
import com.example.base_ij.jwt.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private String password;

    @Column(nullable = false)
    private String passwordConfirm;

    @Column(nullable = false, unique = true)
    private String email;

    public Member(MemberRequestDto memberRequestDto) {
        this.nickname = memberRequestDto.getNickname();
        this.password = memberRequestDto.getPassword();
        this.passwordConfirm = memberRequestDto.getPasswordConfirm();
        this.email = memberRequestDto.getEmail();
    }

}

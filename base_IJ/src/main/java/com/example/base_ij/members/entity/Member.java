package com.example.base_ij.members.entity;

import com.example.base_ij.members.dto.request.MemberRequestDto;
import com.example.base_ij.jwt.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Member member = (Member) o;
        return id != null && Objects.equals(id, member.id);
    }

}

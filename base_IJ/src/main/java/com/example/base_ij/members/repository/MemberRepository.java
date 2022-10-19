package com.example.base_ij.members.repository;

import com.example.base_ij.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByEmail(String email);

}

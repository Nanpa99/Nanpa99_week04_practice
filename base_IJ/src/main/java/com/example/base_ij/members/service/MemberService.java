package com.example.base_ij.members.service;


import com.example.base_ij.members.dto.request.LoginRequestDto;
import com.example.base_ij.members.dto.request.MemberRequestDto;
import com.example.base_ij.members.dto.response.MemberResponseDto;
import com.example.base_ij.members.dto.response.ResponsDto;
import com.example.base_ij.members.entity.Member;
import com.example.base_ij.members.entity.RefreshToken;
import com.example.base_ij.jwt.dto.TokenDto;
import com.example.base_ij.jwt.util.JwtUtil;
import com.example.base_ij.members.repository.MemberRepository;
import com.example.base_ij.members.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service

public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public ResponsDto<?> signup(MemberRequestDto memberRequestDto) {
        // Nickname 중복 확인
        String nickname = memberRequestDto.getNickname();
        Optional<Member> optionalMember = memberRepository.findByNickname(nickname);
        if (optionalMember.isPresent()) {
            return ResponsDto.fail("DUPLICATED_NICKNAME", "중복된 닉네임 입니다.");
        }

        // Email 중복 확인
        String email = memberRequestDto.getEmail();
        Optional<Member> optionalMember1 = memberRepository.findByEmail(email);
        if (optionalMember1.isPresent()) {
            return ResponsDto.fail("DUPLICATED_EMAIL", "중복된 이메일 입니다.");
        }

        // 패스워드 확인
        if (!memberRequestDto.getPassword().equals(memberRequestDto.getPasswordConfirm())) {
            return ResponsDto.fail("PASSWORDS_NOT_ MATCHED", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        }

        memberRequestDto.setEncodePwd(passwordEncoder.encode(memberRequestDto.getPassword()));
        Member member = new Member(memberRequestDto);

        memberRepository.save(member);
        return ResponsDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    @Transactional
    public ResponsDto<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        Member member = memberRepository.findByNickname(loginRequestDto.getNickname()).orElseThrow(
                () -> new RuntimeException("Not matches Nickname")
        );
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())) {
            return ResponsDto.fail("Not matches Password", "비밀번호가 일치하지 않습니다.");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getNickname());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByMemberNickname(loginRequestDto.getNickname());

        if (refreshToken.isPresent()) {
            refreshTokenRepository.save(refreshToken.get().updateToken(tokenDto.getRefreshToken()));
        } else {
            RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getNickname());
            refreshTokenRepository.save(newToken);
        }

        setHeader(response, tokenDto);

        return ResponsDto.success(
                MemberResponseDto.builder()
                        .id(member.getId())
                        .nickname(member.getNickname())
                        .createdAt(member.getCreatedAt())
                        .modifiedAt(member.getModifiedAt())
                        .build()
        );
    }

    private void setHeader(HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        response.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }
}

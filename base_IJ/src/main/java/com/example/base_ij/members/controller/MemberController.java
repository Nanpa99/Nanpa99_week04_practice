package com.example.base_ij.members.controller;

import com.example.base_ij.members.dto.request.LoginRequestDto;
import com.example.base_ij.members.dto.request.MemberRequestDto;
import com.example.base_ij.members.dto.response.ResponsDto;
import com.example.base_ij.jwt.util.JwtUtil;
import com.example.base_ij.security.user.UserDetailsImpl;
import com.example.base_ij.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")

public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping(value = "/signup")
    public ResponsDto<?> signup(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.signup(memberRequestDto);
    }

    @PostMapping(value = "/login")
    public ResponsDto<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return memberService.login(loginRequestDto, response);
    }

    @GetMapping(value = "/issue/token")
    public ResponsDto<?> issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
        response.addHeader(JwtUtil.ACCESS_TOKEN,jwtUtil.createToken(userDetails.getMember().getPassword(), "Access"));
        return ResponsDto.success("Success IssuedToken");
    }
}
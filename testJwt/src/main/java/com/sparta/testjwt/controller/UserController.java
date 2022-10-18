package com.sparta.testjwt.controller;

import com.sparta.testjwt.service.UserService;
import com.sparta.testjwt.dto.request.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/user/join")
    public String join (@RequestBody UserDto dto){
        return userService.addUser(dto);
    }

//    // 회원 개인페이지
//    @GetMapping("/user/mypage")
//    public UserDto getUserInfo(){
//        return userService.getUserInfo();
//    }

}

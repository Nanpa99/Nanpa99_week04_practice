package com.sparta.testjwt.dto.request;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String content;
    private String username;
}

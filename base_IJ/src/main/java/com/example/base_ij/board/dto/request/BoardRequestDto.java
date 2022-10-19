package com.example.base_ij.board.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String nickname;
}

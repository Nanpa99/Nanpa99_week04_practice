package com.example.base_ij.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor

public class CommentRequestDto {

    private Long boardId;

    @NotBlank
    private String content;
}

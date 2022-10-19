package com.example.base_ij.comment.controller;

import com.example.base_ij.comment.dto.request.CommentRequestDto;
import com.example.base_ij.comment.dto.response.CommentResponseDto;
import com.example.base_ij.comment.entity.Comment;
import com.example.base_ij.comment.repository.CommentRepositoty;
import com.example.base_ij.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentRepositoty commentRepositoty;


    @GetMapping("/comment")
    public List<CommentResponseDto> allBoard(){

        return commentService.commentAll();
    }

    @PostMapping("/comment")
    public Comment postBoard(@RequestBody CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        return commentRepositoty.save(comment);

    }
}

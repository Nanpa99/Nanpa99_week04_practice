package com.example.base_ij.comment.controller;
import com.example.base_ij.comment.dto.request.CommentRequestDto;
import com.example.base_ij.comment.service.CommentService;
import com.example.base_ij.global.GlobalResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.example.base_ij.members.dto.response.ResponsDto;
import javax.servlet.http.HttpServletRequest;

@Validated
@RequiredArgsConstructor
@RestController
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/auth/comment")
    public GlobalResDto createComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.createComment(requestDto, request);
    }

    @GetMapping("/comment/{id}")
    public GlobalResDto getAllComments(@PathVariable Long id) {
        return commentService.getAllCommentsByPost(id);
    }

    @PutMapping("/auth/comment/{id}")
    public GlobalResDto updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.updateComment(id, requestDto, request);
    }

    @DeleteMapping("/auth/comment/{id}")
    public GlobalResDto deleteComment(@PathVariable Long id, HttpServletRequest request) {
        return commentService.deleteComment(id, request);
    }
}


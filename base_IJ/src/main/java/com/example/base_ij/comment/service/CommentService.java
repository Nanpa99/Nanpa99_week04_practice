package com.example.base_ij.comment.service;

import com.example.base_ij.comment.dto.response.CommentResponseDto;
import com.example.base_ij.comment.entity.Comment;
import com.example.base_ij.comment.repository.CommentRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepositoty commentRepositoty;

    @Transactional
    public List<CommentResponseDto> commentAll(){
        List<Comment> comment_lst = commentRepositoty.findAll();
        List<CommentResponseDto> commentResponseDto_lst = new ArrayList<>();

        for(Comment comment : comment_lst){
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDto_lst.add(commentResponseDto);

        }
        return commentResponseDto_lst;

    }

}

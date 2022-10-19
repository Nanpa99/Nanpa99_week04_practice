package com.sparta.testjwt.service;

import com.sparta.testjwt.dto.request.CommentRequestDto;
import com.sparta.testjwt.entity.Comment;
import com.sparta.testjwt.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Boolean update(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        comment.updateComment(requestDto);
        return true;
    }
}

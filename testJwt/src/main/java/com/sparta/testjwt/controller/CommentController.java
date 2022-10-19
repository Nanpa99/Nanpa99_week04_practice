package com.sparta.testjwt.controller;

import com.sparta.testjwt.dto.request.CommentRequestDto;
import com.sparta.testjwt.entity.Board;
import com.sparta.testjwt.entity.Comment;
import com.sparta.testjwt.repository.BoardRepository;
import com.sparta.testjwt.repository.CommentRepository;
import com.sparta.testjwt.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class CommentController {
    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    // 댓글 생성
    @PostMapping("/comment")
    public Comment createComment(@RequestParam Long boardId, @RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        Optional<Board> optionalBoard  = boardRepository.findById(boardId);
        optionalBoard.ifPresent(board -> board.addComment(comment));
        return commentRepository.save(comment);
    }

    // 댓글 수정
    @PutMapping("/comment/{id}")
    public Boolean updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto){
        return commentService.update(id, requestDto);
    }
}

package com.sparta.testjwt.controller;

import com.sparta.testjwt.domain.Board;
import com.sparta.testjwt.dto.request.BoardRequestDto;
import com.sparta.testjwt.repository.BoardRepository;
import com.sparta.testjwt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // 전체 게시글 보기
    @GetMapping("/boards")
    public List<Board> getBoards(){
        return boardRepository.findAll();
    }

    // 게시글 등록
    @PostMapping("/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }
}

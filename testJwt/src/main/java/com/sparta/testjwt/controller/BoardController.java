package com.sparta.testjwt.controller;

import com.sparta.testjwt.dto.response.BoardListResponseDto;
import com.sparta.testjwt.dto.response.BoardResponseDto;
import com.sparta.testjwt.entity.Board;
import com.sparta.testjwt.dto.request.BoardRequestDto;
import com.sparta.testjwt.repository.BoardRepository;
import com.sparta.testjwt.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // 전체 게시글 보기
    @GetMapping("/boards")
    public BoardListResponseDto getBoards(){
        return boardService.findAll();
    }

    // 게시글 등록
    @PostMapping("/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return board;
    }

    // 게시글 조회
    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.findBoardId(id);
    }


    //게시글 수정
    @PutMapping("/boards/{id}")
    public Boolean updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.update(id, requestDto);
    }


    //게시글 삭제
    @DeleteMapping("/boards/{id}")
    public Long deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }
}
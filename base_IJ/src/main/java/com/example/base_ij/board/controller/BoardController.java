package com.example.base_ij.board.controller;

import com.example.base_ij.board.dto.request.BoardRequestDto;
import com.example.base_ij.board.dto.response.BoardListResponseDto;
import com.example.base_ij.board.dto.response.BoardResponseDto;
import com.example.base_ij.global.GlobalResDto;
import com.example.base_ij.board.repository.BoardRepository;
import com.example.base_ij.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;

    // 전체 게시글 보기
    @GetMapping("/boards")
    public BoardListResponseDto getBoards(){
        return boardService.getBoards();
    }

    // 게시글 등록
    @PostMapping("/auth/boards")
    public GlobalResDto createBoard(@RequestBody BoardRequestDto requestDto, HttpServletRequest httpServletRequest){
        return boardService.saveBoard(requestDto, httpServletRequest);
    }

    // 게시글 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }


    //게시글 수정
    @PutMapping("/auth/boards/{id}")
    public GlobalResDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }


    //게시글 삭제
    @DeleteMapping("/auth/boards/{id}")
    public GlobalResDto deleteBoard(@PathVariable Long id) {
        return boardService.deleteBoard(id);
    }
}
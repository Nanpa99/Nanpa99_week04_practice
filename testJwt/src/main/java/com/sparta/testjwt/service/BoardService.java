package com.sparta.testjwt.service;

import com.sparta.testjwt.dto.request.BoardRequestDto;
import com.sparta.testjwt.dto.response.BoardListResponseDto;
import com.sparta.testjwt.dto.response.BoardResponseDto;
import com.sparta.testjwt.entity.Board;
import com.sparta.testjwt.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public BoardListResponseDto findBoards() {
        List<Board> boards = boardRepository.findAll();
        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
        for (Board board : boards){
            boardListResponseDto.addBoard(new BoardResponseDto(board));
        }
        return boardListResponseDto;
    }

    // id를 찾고 그 id에 있는 정보를 ResponseDto 에 담아 필요한 정보만 ResponseDto 타입으로 리턴 해주는 함수
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public BoardResponseDto findBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        return new BoardResponseDto(board);
    }

    // id에 해당하는 게시글 수정하는 함수
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언합니다.
    public Boolean update(Long id, BoardRequestDto requestDto) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        board.update(requestDto);
        return true;
    }
}

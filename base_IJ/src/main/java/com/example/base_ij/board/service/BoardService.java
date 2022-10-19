package com.example.base_ij.board.service;


import com.example.base_ij.board.dto.request.BoardRequestDto;
import com.example.base_ij.board.dto.response.BoardListResponseDto;
import com.example.base_ij.board.dto.response.BoardResponseDto;
import com.example.base_ij.board.entity.Board;
import com.example.base_ij.comment.repository.CommentRepositoty;
import com.example.base_ij.global.GlobalResDto;
import com.example.base_ij.board.repository.BoardRepository;
import com.example.base_ij.jwt.util.JwtUtil;
import com.example.base_ij.members.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final CommentRepositoty commentRepositoty;
    private final JwtUtil jwtUtil;


    @Transactional
    public GlobalResDto saveBoard(BoardRequestDto boardRequestDto, HttpServletRequest httpServletRequest) {

        // boardRepository.save(new Board(boardRequestDto));

        Member member = validateMember(httpServletRequest);
        if (null == member) {
            return new GlobalResDto("INVALID_TOKEN", HttpStatus.FORBIDDEN.value());
        }

        Board board = Board.builder()
                .nickname(boardRequestDto.getNickname())
                .content(boardRequestDto.getContent())
                .title(boardRequestDto.getTitle())
                .member(member)
                .build();

        boardRepository.save(board);

        return new GlobalResDto("Success Save Course", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<BoardResponseDto> getBoard(Long id) {

        Board board = checkBoard(boardRepository, id);

        return ResponseEntity.ok(new BoardResponseDto(board));
    }

    @Transactional(readOnly = true)
    public BoardListResponseDto getBoards() {

        BoardListResponseDto boardListResponseDto = new BoardListResponseDto();
        List<Board> boards = boardRepository.findAll();

        for (Board board : boards) {
            boardListResponseDto.addBoard(new BoardResponseDto(board));
        }

        return boardListResponseDto;
    }

    @Transactional
    public GlobalResDto updateBoard(Long id, BoardRequestDto boardRequestDto) {


        Board board = checkBoard(boardRepository, id);
        board.boardUpdate(boardRequestDto);

        return new GlobalResDto("Success Update Course", HttpStatus.OK.value());

    }

    @Transactional
    public GlobalResDto deleteBoard(Long id) {
        Board board = checkBoard(boardRepository, id);
        boardRepository.delete(board);
        return new GlobalResDto("Success Delete Course", HttpStatus.OK.value());
    }

    private Board checkBoard(BoardRepository boardRepository, Long id) {
        return boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found Course")
        );
    }

    @Transactional(readOnly = true)
    public Board isPresentPost(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        return optionalBoard.orElse(null);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!jwtUtil.tokenValidation(request.getHeader("Access_Token"))) {
            return null;
        }
        return jwtUtil.getMemberFromAuthentication();

    }
}

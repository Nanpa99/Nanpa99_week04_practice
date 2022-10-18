package com.example.base_ij.board.service;

import com.example.base_ij.board.dto.request.BoardRequestDto;
import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired//DI를 가져옴
    private BoardRepository boardRepository;

    public List<Board> index() {
        return boardRepository.findAll();
    }

    public Board show(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board create(BoardRequestDto dto) {
        Board board = dto.toEntity();
        if (board.getId()!=null) {
            return null;
        }
        return boardRepository.save(board);
    }

    public Board update(Long id,BoardRequestDto dto) {
        //1. 수정 엔티티 생성
        Board board = dto.toEntity();
        log.info("id: {},article: {}", id, board.toString());

        //2. 대상 엔티티 조회
        Board target = boardRepository.findById(id).orElse(null);

        //3.잘못된 요청 처리(대상이 없거나 ,id가 다른경우)
        if (target == null || id != board.getId()) {
            //400 잘못된 요청
            log.info("잘못된 요청! id: {},article: {}", id, board.toString());
            return null;
        }
        //4.정상 업데이트 및 정상 응답
        target.patch(board);
        Board updated = boardRepository.save(target);
        return updated;
    }

    public Board delete(Long id) {
        //대상찾기
        Board target = boardRepository.findById(id).orElse(null);
        //잘못된 요청 처리
        if(target == null) {
            return null;
        }
        //대상 삭제
        boardRepository.delete(target);
        return target;
    }
}

package com.example.base_ij.board.controller;

import com.example.base_ij.board.dto.request.BoardRequestDto;
import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired //외부에서 가져온다.
    private BoardService boardService;

    //get
    @GetMapping("/boards")
    public List<Board> index() {
        return boardService.index();
    }

    //get 1개만
    @GetMapping("/boards/{id}")
    public Board show(@PathVariable Long id) {
        return boardService.show(id);
    }

    //post
    @PostMapping("/auth/boards")
    public ResponseEntity<Board> create(@RequestBody/*json형식으로 받을때는 body내용만 받아줘야한다.*/ BoardRequestDto dto/*데이터를 받아올때 dto로 받아준다*/) {
        Board created = boardService.create(dto);//dto를 엔티티로 바꿔준다.
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //patch
    @PatchMapping("/auth/boards/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id, @RequestBody BoardRequestDto dto) {
        Board updated = boardService.update(id,dto);
        return (updated!=null) ? ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    //DELETE
    @DeleteMapping("/auth/boards/{id}")
    public ResponseEntity<Board> delete(@PathVariable Long id) {
        Board deleted = boardService.delete(id);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }




}

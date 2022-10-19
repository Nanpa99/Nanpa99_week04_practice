package com.sparta.testjwt.dto.response;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class BoardListResponseDto {
    List<BoardResponseDto> boardResponseDtos = new LinkedList<>();

    public void addBoard(BoardResponseDto boardResponseDto){
        boardResponseDtos.add(boardResponseDto);
    }
}

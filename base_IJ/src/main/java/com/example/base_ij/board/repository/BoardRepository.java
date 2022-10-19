package com.example.base_ij.board.repository;

import com.example.base_ij.board.entity.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BoardRepository extends CrudRepository<Board/*관리대상 엔티티*/,Long/*그 엔티티의 대표값*/> {
    //그냥 리포지토리를 만들수있지만 미리 만들어준게 있다. 그게 CrudRepository이거

    @Override
    ArrayList<Board> findAll();
}


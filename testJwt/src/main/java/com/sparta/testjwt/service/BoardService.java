package com.sparta.testjwt.service;

import com.sparta.testjwt.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository repository;


}

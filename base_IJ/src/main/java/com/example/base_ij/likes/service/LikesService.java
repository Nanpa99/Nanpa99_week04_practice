package com.example.base_ij.likes.service;

import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.repository.BoardRepository;
import com.example.base_ij.board.service.BoardService;
import com.example.base_ij.global.GlobalResDto;
import com.example.base_ij.jwt.util.JwtUtil;
import com.example.base_ij.likes.entity.Likes;
import com.example.base_ij.likes.repository.LikesRepositoty;
import com.example.base_ij.members.entity.Member;
import com.example.base_ij.members.repository.MemberRepository;
import com.example.base_ij.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class LikesService {

    private final LikesRepositoty likesRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    @Autowired
    public LikesService(BoardRepository boardRepository, MemberRepository memberRepository, LikesRepositoty likesRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.likesRepository = likesRepository;
    }

    public String GoLikes(Long boardId, UserDetailsImpl userDetailsImpl){
        Member member = memberRepository.findByEmail(userDetailsImpl.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다.")
        );
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new IllegalArgumentException("게시글 ID를 찾을 수 없습니다.")
        );

        Optional<Likes> optionalLikes = likesRepository.findByBoardAndMember(board, member);

        if (optionalLikes.isPresent()){
            likesRepository.delete(optionalLikes.get());

            return "좋아요취소";
        }else{
            Likes likes = new Likes(member, board);
            likesRepository.save(likes);

            return "좋아요";
        }

    }


//
//    @Transactional
//    public Member validateMember(HttpServletRequest request) {
//        if (!jwtUtil.tokenValidation(request.getHeader("Access_Token"))) {
//            return null;
//        }
//        return jwtUtil.getMemberFromAuthentication();
//    }


}

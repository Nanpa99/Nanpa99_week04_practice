package com.example.base_ij.comment.service;


import com.example.base_ij.board.entity.Board;
import com.example.base_ij.board.service.BoardService;
import com.example.base_ij.comment.dto.request.CommentRequestDto;
import com.example.base_ij.comment.dto.response.CommentResponseDto;
import com.example.base_ij.comment.entity.Comment;
import com.example.base_ij.global.GlobalResDto;
import com.example.base_ij.members.dto.response.ResponsDto;
import com.example.base_ij.comment.repository.CommentRepositoty;
import com.example.base_ij.jwt.util.JwtUtil;
import com.example.base_ij.members.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepositoty commentRepository;
    private final JwtUtil jwtUtil;
    private final BoardService boardService;

    @Transactional
    public GlobalResDto createComment(CommentRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Access_Token")) {
            return new GlobalResDto("MEMBER_NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        if (null == request.getHeader("Refresh_Token")) {
            return new GlobalResDto("MEMBER_NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        Member member = validateMember(request);
        if (null == member) {
            return new GlobalResDto("INVALID_TOKEN", HttpStatus.NO_CONTENT.value());
        }

        Board board = boardService.isPresentPost(requestDto.getBoardId());
        if (null == board) {
            return new GlobalResDto("NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        Comment comment = Comment.builder()
                .member(member)
                .board(board)
                .content(requestDto.getContent())
                .build();

        commentRepository.save(comment);

        return new GlobalResDto("Success Create Comment", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public GlobalResDto getAllCommentsByPost(Long boardId) {
        Board board = boardService.isPresentPost(boardId);
        if (null == board) {
            return new GlobalResDto("NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        List<Comment> commentList = commentRepository.findAllByBoard(board);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseDtoList.add(
                    CommentResponseDto.builder()
                            .id(comment.getId())
                            .author(comment.getMember().getNickname())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .modifiedAt(comment.getModifiedAt())
                            .build()
            );
        }
        return new GlobalResDto("Success Get All Comment", HttpStatus.OK.value());
    }

    @Transactional
    public ResponseDto<?> updateComment(Long id, CommentRequestDto requestDto, HttpServletRequest request) {
        if (null == request.getHeader("Refresh-Token")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        if (null == request.getHeader("Authorization")) {
            return ResponseDto.fail("MEMBER_NOT_FOUND",
                    "로그인이 필요합니다.");
        }

        Member member = validateMember(request);
        if (null == member) {
            return ResponseDto.fail("INVALID_TOKEN", "Token이 유효하지 않습니다.");
        }

        Post post = postService.isPresentPost(requestDto.getPostId());
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

        Comment comment = isPresentComment(id);
        if (null == comment) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 댓글 id 입니다.");
        }

        if (comment.validateMember(member)) {
            return ResponseDto.fail("BAD_REQUEST", "작성자만 수정할 수 있습니다.");
        }

        comment.update(requestDto);

        return ResponseDto.success(
                CommentResponseDto.builder()
                        .id(comment.getId())
                        .author(comment.getMember().getNickname())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .modifiedAt(comment.getModifiedAt())
                        .build()
        );

        return new GlobalResDto("Success Update Comment", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResDto deleteComment(Long id, HttpServletRequest request) {
        if (null == request.getHeader("Access_Token")) {
            return new GlobalResDto("MEMBER_NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        if (null == request.getHeader("Refresh_Token")) {
            return new GlobalResDto("MEMBER_NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        Member member = validateMember(request);
        if (null == member) {
            return new GlobalResDto("INVALID_TOKEN", HttpStatus.FORBIDDEN.value());
        }

        Comment comment = isPresentComment(id);
        if (null == comment) {
            return new GlobalResDto("NOT_FOUND", HttpStatus.NOT_FOUND.value());
        }

        if (comment.validateMember(member)) {
            return new GlobalResDto("BAD_REQUEST", HttpStatus.BAD_REQUEST.value());
        }

        commentRepository.delete(comment);
        return new GlobalResDto("Success Delete Comment", HttpStatus.OK.value());
    }

    @Transactional(readOnly = true)
    public Comment isPresentComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        return optionalComment.orElse(null);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!jwtUtil.tokenValidation(request.getHeader("Access_Token"))) {
            return null;
        }
        return jwtUtil.getMemberFromAuthentication();
    }
}



package com.example.base_ij.likes.controller;

import com.example.base_ij.likes.service.LikesService;
import com.example.base_ij.security.user.UserDetailsImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Getter
@Setter

public class LikesController {

    private final LikesService likesService;

    @Autowired
    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @GetMapping("/api/board/{boardId}/like")
    public String createLikes(@PathVariable Long boardId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return likesService.GoLikes(boardId, userDetails);
    }


}

package com.jungle.board.post.controller;

import com.jungle.board.common.CommonResponse;
import com.jungle.board.post.domain.dto.CreatePostRequest;
import com.jungle.board.post.service.PostService;
import com.jungle.board.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("")
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @RequestBody CreatePostRequest createPostRequest) {
        try {
            postService.create(user, createPostRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonResponse.builder()
                    .code("400")
                    .message("게시물 생성에 실패했습니다.")
                    .build());
        }
    }
}

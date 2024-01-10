package com.jungle.board.comment;

import com.jungle.board.comment.domain.dto.CreateCommentRequest;
import com.jungle.board.comment.service.CommentService;
import com.jungle.board.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> create(@AuthenticationPrincipal User user, @RequestBody CreateCommentRequest createCommentRequest, @PathVariable Long postId) {
        commentService.create(user, createCommentRequest, postId);
        return ResponseEntity.ok().build();
    }
}

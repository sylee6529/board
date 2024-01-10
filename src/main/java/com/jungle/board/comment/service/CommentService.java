package com.jungle.board.comment.service;

import com.jungle.board.comment.domain.Comment;
import com.jungle.board.comment.domain.dto.CreateCommentRequest;
import com.jungle.board.comment.repository.CommentRepository;
import com.jungle.board.post.domain.Post;
import com.jungle.board.post.repository.PostRepository;
import com.jungle.board.post.service.PostService;
import com.jungle.board.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void create(User user, CreateCommentRequest createCommentRequest, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
        commentRepository.save(Comment.builder()
                .content(createCommentRequest.getContent())
                .user(user)
                .post(post)
                .status("ACTIVE")
                .build());
    }
}

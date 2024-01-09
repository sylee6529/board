package com.jungle.board.post.service;

import com.jungle.board.board.domain.Board;
import com.jungle.board.board.repository.BoardRepository;
import com.jungle.board.post.domain.Post;
import com.jungle.board.post.domain.dto.CreatePostDto;
import com.jungle.board.post.repository.PostRepository;
import com.jungle.board.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final BoardRepository boardRepository;

    public void create(final User user, final CreatePostDto createPostDto) {
        final Board board = boardRepository.findById(createPostDto.getBoardId())
                .orElseThrow(() -> new RuntimeException("Board not found"));

        postRepository.save(Post.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .user(user)
                .board(board)
                .likeCount(0)
                .viewCount(0)
                .status("ACTIVE")
                .build());
    }
}

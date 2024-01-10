package com.jungle.board.post.repository;

import com.jungle.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByBoardId(Long boardId);

    Post findById(long id);
}

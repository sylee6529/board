package com.jungle.board.board.domain.dto;

import com.jungle.board.comment.domain.Comment;
import com.jungle.board.post.domain.Post;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetPostDto {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private Long userId;

    public static GetPostDto from(Post post) {
        return new GetPostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getViewCount(),
                post.getUser().getId()
        );
    }
}

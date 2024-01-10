package com.jungle.board.post.domain.dto;

import com.jungle.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetPostResponse {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private Long userId;
    private Long boardId;
    private String boardName;
    private String userNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<GetCommentDto> comments;

    public static GetPostResponse from(Post post) {
        return new GetPostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getViewCount(),
                post.getUser().getId(),
                post.getBoard().getId(),
                post.getBoard().getName(),
                post.getUser().getNickname(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getComments().stream()
                        .map(GetCommentDto::from)
                        .toList()
        );
    }
}

package com.jungle.board.post.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class CreatePostRequest {
    private String title;
    private String content;
    private Long boardId;
}

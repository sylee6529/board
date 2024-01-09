package com.jungle.board.board.domain.dto;

import com.jungle.board.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetCommentDto {
    private Long id;
    private String content;
    private String userId;
    private String userNickname;

    public static GetCommentDto from(Comment comment) {
        return new GetCommentDto(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUserId(),
                comment.getUser().getNickname()
        );
    }
}

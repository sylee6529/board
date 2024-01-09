package com.jungle.board.board.domain.dto;

import com.jungle.board.board.domain.Board;
import com.jungle.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class GetBoardResponse {
    private Long id;
    private String boardName;
    private String boardDescription;
    private List<GetPostDto> posts;

    public static GetBoardResponse from(Board board) {
        return new GetBoardResponse(
                board.getId(),
                board.getName(),
                board.getDescription(),
                board.getPosts().stream()
                        .map(GetPostDto::from)
                        .collect(Collectors.toList())
        );
    }
}

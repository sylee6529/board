package com.jungle.board.board.service;

import com.jungle.board.board.domain.Board;
import com.jungle.board.board.domain.dto.GetBoardResponse;
import com.jungle.board.board.domain.dto.GetPostDto;
import com.jungle.board.board.repository.BoardRepository;
import com.jungle.board.post.domain.Post;
import com.jungle.board.post.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRepository postRepository;

    public GetBoardResponse getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
//        List<Post> posts = postRepository.findAllByBoardId(boardId);
        return GetBoardResponse.from(board);
    }
}

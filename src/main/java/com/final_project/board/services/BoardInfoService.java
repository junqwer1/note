package com.final_project.board.services;

import com.final_project.board.entities.Board;
import com.final_project.board.repositories.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardInfoService {

    private final BoardRepository repository;

    public List<Board> getList() {
        return repository.findAll();
    }

    @Transactional
    public Board get(Long boardId) {
        Board board = repository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        board.setViewCount(board.getViewCount() + 1);

        return board;
    }
}

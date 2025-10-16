package com.final_project.board.services;

import com.final_project.board.entities.Board;
import com.final_project.board.repositories.BoardRepository;
import com.final_project.board.services.dto.BoardDetailResponse;
import com.final_project.note.entities.Note;
import com.final_project.note.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardInfoService {

    private final BoardRepository boardRepository;
    private final NoteRepository noteRepository;

    @Transactional
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Transactional
    public BoardDetailResponse get(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글을 찾을 수 없습니다."));

        board.setViewCount(board.getViewCount() + 1);

        Note noteDetail = noteRepository.findById(board.getNoteId())
                .orElseThrow(() -> new IllegalStateException("게시글의 원본 노트를 찾을 수 없습니다."));

        BoardDetailResponse bdr = BoardDetailResponse.builder()
                // --- Board에서 가져온 정보 ---
                .boardId(board.getBoardId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .createdAt(board.getCreatedAt())
                .viewCount(board.getViewCount())
                // --- Note에서 실시간으로 가져온 정보 ---
                .summary(noteDetail.getSummary())
                .contents(noteDetail.getContent())
                .tags(noteDetail.getTags())
                .build();

        return bdr;
    }
}

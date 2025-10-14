package com.final_project.board.services;

import com.final_project.board.controllers.RequestBoard;
import com.final_project.board.entities.Board;
import com.final_project.board.repositories.BoardRepository;
import com.final_project.member.Member;
import com.final_project.member.MemberUtil;
import com.final_project.note.entities.Note;
import com.final_project.note.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
public class BoardCreateService {

    private final NoteRepository noteRepository;
    private final BoardRepository boardRepository;
    private final MemberUtil memberUtil;

    public Board boardCreate(RequestBoard form) throws BadRequestException {
        // 1. noteId로 원본 노트를 찾습니다.
        Note note = noteRepository.findById(form.getNoteId())
                .orElseThrow(() -> new IllegalArgumentException("노트를 찾을 수 없습니다."));

        Member member = memberUtil.getMember();
        if (member == null) {
            throw new BadRequestException("로그인 후 이용해주세요.");
        }

        if (!note.getMemberId().equals(member.getMemberId())) {
            throw new SecurityException("자신이 작성한 노트만 게시할 수 있습니다."); // 접근 거부 예외 발생
        }

        // 2. 노트의 정보를 '복사'하여 게시글 엔티티를 생성
        Board board = Board.builder()
                .title(note.getTitle())       // 노트의 제목을 복사
                .author(note.getMemberId()) // 노트의 memberId를 복사
                .viewCount(0L)
                .build();

        return boardRepository.saveAndFlush(board);

    }
}

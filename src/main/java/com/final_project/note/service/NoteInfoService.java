package com.final_project.note.service;

import com.final_project.note.entities.Note;
import com.final_project.note.entities.QNote;
import com.final_project.note.repositories.NoteRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Lazy
@Service
@RequiredArgsConstructor
public class NoteInfoService {
    private final NoteRepository noteRepository;
    private final HttpServletRequest request;
    private final JPAQueryFactory queryFactory;

    public Note get(String id){
        BooleanBuilder builder =  new BooleanBuilder();
        BooleanBuilder orBuilder = new BooleanBuilder();
        QNote note = QNote.note;
        builder.and(note.noteId.eq(id));

        Note item = noteRepository.findOne(builder).orElseThrow();

        return item;

    }
}

package com.final_project.note.entities;

import com.final_project.global.Entities.BaseEntity;
import com.final_project.note.constants.IsContentStatus;
import com.final_project.note.constants.IsNoteStatus;
import com.final_project.note.constants.NoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "NOTE")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Note extends BaseEntity {

    @Id
    @Column(name = "note_id", length = 36)
    private String noteId; //노트 id

    @Column(name = "member_id")
    private String memberId; //작성자 회원 ID

    @Column(nullable = false)
    private String title; // 제목

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary; // AI 요약 내용

    @Enumerated(EnumType.STRING)
    @Column(name = "note_status", nullable = false)
    private NoteStatus noteStatus; // 노트 처리 상태

    @Lob
    private String content; //내용

    @Lob
    private String privateMemo; // 개인 메모

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private IsNoteStatus isPublicNote; //노트 공개 여부

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private IsContentStatus isPublicContent; //내용 공개 여부

}

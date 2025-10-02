package com.final_project.note.entities;

import com.final_project.global.Entities.BaseEntity;
import com.final_project.note.constants.IsContentStatus;
import com.final_project.note.constants.IsNoteStatus;
import com.final_project.note.constants.NoteStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "NOTE")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Note extends BaseEntity {

    @Id
    @Column(name = "note_id", length = 36)
    private String noteId; //노트 id

    @Column(name = "member_id", length = 45)
    private String memberId; //작성자 회원 ID

    @Column(nullable = false)
    private String youtubeUrl; // 노트가 생성된 원본 유튜브 URL

    @Column(nullable = false)
    private String title; // 제목

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String summary; // AI 요약 내용

    @Enumerated(EnumType.STRING)
    @Column(name = "note_status", nullable = false)
    private NoteStatus noteStatus; // 노트 처리 상태

    @Lob
    private String privateMemo; // 개인 메모

    // Note 하나는 여러 개의 Tag를 가짐 (1:N 관계)
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<>();

    // 내용
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Content> content = new ArrayList<>();

    // 퀴즈
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz> quizzes = new ArrayList<>();

    // 퀴즈 정답
    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Quiz> quizResult = new ArrayList<>();


}

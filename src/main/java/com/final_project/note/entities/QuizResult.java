package com.final_project.note.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "quiz_results")
@NoArgsConstructor @AllArgsConstructor
public class QuizResult {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @Column(length = 45, nullable = false)
    private String memberId; // 퀴즈를 푼 회원의 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noteId", nullable = false)
    @JsonIgnore
    private Note note; // 응시한 퀴즈가 포함된 노트

    @Column(nullable = false)
    private int score; // 퀴즈 결과 점수

    @Lob // JSON 데이터를 저장하기 위해 CLOB 또는 TEXT 타입으로 매핑
    @Column(nullable = false)
    private String submittedAnswers; // 사용자가 제출한 답안 (JSON 형식)

    @CreationTimestamp // 엔티티가 처음 저장될 때 현재 시간이 자동으로 기록됨
    @Column(nullable = false, updatable = false)
    private LocalDateTime solvedAt; // 퀴즈를 푼 날짜

}

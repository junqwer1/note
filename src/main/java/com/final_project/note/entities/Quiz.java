package com.final_project.note.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "questions")
public class Quiz {

    @Id @GeneratedValue
    private Long quizId;

    @Column(name = "question_text", nullable = false)
    private String question; // 퀴즈 질문

    @Lob
    @Column(nullable = false)
    private String options;

    @Column(nullable = false)
    private String answer; // 정답

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id")
    @JsonIgnore
    private Note note;


}

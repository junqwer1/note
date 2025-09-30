package com.final_project.note.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.final_project.note.constants.IsContentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id @GeneratedValue
    private Long id;

    @Lob
    private String text; // 실제 내용

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IsContentStatus isPublic; // 각 내용의 공개 여부

    @ManyToOne(fetch = FetchType.LAZY) // Note와 다대일 관계
    @JoinColumn(name = "noteId")
    @JsonIgnore
    private Note note;
}
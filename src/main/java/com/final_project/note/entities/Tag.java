package com.final_project.note.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Tag {
    @Id @GeneratedValue
    private Long tagId;

    @Column(nullable = false)
    private String name; // "tag"에 해당하는 필드

    @Column(length = 500)
    private String description; // // 태그에 대한 설명

    // Tag는 Note에 속해있는 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "note_id") // 외래 키
    @JsonIgnore // API 응답 시 Note -> Tag -> Note 무한 루프 방지
    private Note note;
}

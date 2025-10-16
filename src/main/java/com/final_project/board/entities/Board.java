package com.final_project.board.entities;

import com.final_project.global.Entities.BaseEntity;
import com.final_project.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long boardId; // 게시판 ID (PK)

    @Column(name = "author", nullable = false)
    private String author; // 작성자

    @Column(nullable = false, length = 255)
    private String title; // 노트 제목

    private long viewCount; // 조회수

    @Column(name = "note_id", nullable = false, unique = true)
    private String noteId; // 원본 노트 ID (게시글 생성 시 사용)

}

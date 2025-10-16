package com.final_project.board.services.dto;

import com.final_project.note.entities.Content;
import com.final_project.note.entities.Tag;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BoardDetailResponse {

    private Long boardId;
    private String author;
    private String title;
    private LocalDateTime createdAt;
    private Long viewCount;

    private String summary;       // AI 요약
    private List<Content> contents; // 전체 내용 목록
    private List<Tag> tags;         // 태그 목록

}

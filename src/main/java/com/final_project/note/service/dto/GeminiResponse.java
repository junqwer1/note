package com.final_project.note.service.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GeminiResponse {
    private String youtubeUrl;
    private String summary;
    private List<QuizDTO> quiz;
    private List<TagDTO> tags;
}

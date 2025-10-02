package com.final_project.note.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class QuizDTO {

    @JsonProperty("question_text")
    private String question;   // 퀴즈 질문

    private List<String> options; // 4개의 보기 목록
    private String answer;     // 정답
}
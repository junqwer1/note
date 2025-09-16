package com.final_project.note.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestNote {
    @NotBlank
    private String title; //제목

    private String content; //내용

    private String private_memo; //개인 메모

    @NotBlank
    private String summary;
}

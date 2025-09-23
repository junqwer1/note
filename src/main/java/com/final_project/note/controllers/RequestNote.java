package com.final_project.note.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestNote {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "유튜브 URL을 입력해주세요.")
    private String youtubeUrl;
}

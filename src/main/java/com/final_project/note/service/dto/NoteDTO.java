package com.final_project.note.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class NoteDTO {

    @Data
    public static class CreateRequest {
        private String youtubeUrl;
    }

    @Data
    public static class UpdateRequest {
        private String title;
        private String privateMemo;
    }
}

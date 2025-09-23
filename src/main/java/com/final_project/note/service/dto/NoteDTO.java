package com.final_project.note.service.dto;

import lombok.Getter;
import lombok.Setter;

public class NoteDTO {

    @Getter
    @Setter
    public static class CreateRequest{
        private String youtubeUrl;
    }

    @Getter
    @Setter
    public static class UpdateRequest{
        private String title;
        private String privateMemo;
    }
}

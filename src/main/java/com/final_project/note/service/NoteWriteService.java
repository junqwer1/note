package com.final_project.note.service;

import com.final_project.note.constants.IsContentStatus;
import com.final_project.note.constants.IsNoteStatus;
import com.final_project.note.constants.NoteStatus;
import com.final_project.note.controllers.RequestNote;
import com.final_project.note.entities.Note;
import com.final_project.note.repositories.NoteRepository;
import com.final_project.note.service.dto.GeminiResponse;
import com.final_project.note.service.dto.PythonApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Lazy
@Service
@RequiredArgsConstructor
public class NoteWriteService {

    private final RestTemplate restTemplate;
    private final NoteRepository noteRepository;

    public Note createNote(RequestNote form) {

        Note note = new Note();
        note.setNoteId(UUID.randomUUID().toString());
        note.setTitle(form.getTitle());
        note.setSummary("");
        note.setNoteStatus(NoteStatus.PROCESSING);
        note.setIsPublicNote(IsNoteStatus.PUBLIC);
        note.setIsPublicContent(IsContentStatus.PUBLIC);

        try{
            final String pythonApiUrl = "http://localhost:5000/process";

            PythonApiRequest pythonApiRequest = new PythonApiRequest(form.getYoutubeUrl());
            HttpEntity<RequestNote> request = new HttpEntity<>(form);

            GeminiResponse response = restTemplate.postForObject(pythonApiUrl, request, GeminiResponse.class);

            if (response == null || response.getSummary() == null) {
                throw new RuntimeException("노트 생성에 실패했습니다.");
            }

            note.setTitle(response.getYoutubeUrl());
            note.setSummary(response.getSummary());
            note.setNoteStatus(NoteStatus.COMPLETED);
        } catch (Exception e) {
            note.setNoteStatus(NoteStatus.FAILED);
            throw new RuntimeException("노트 생성 실패" + e.getMessage());
        }

        return noteRepository.save(note);

    }
}

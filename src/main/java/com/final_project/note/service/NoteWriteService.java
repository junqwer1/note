package com.final_project.note.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.final_project.member.Member;
import com.final_project.member.MemberUtil;
import com.final_project.note.constants.NoteStatus;
import com.final_project.note.controllers.RequestNote;
import com.final_project.note.entities.Note;
import com.final_project.note.entities.Quiz;
import com.final_project.note.entities.Tag;
import com.final_project.note.repositories.NoteRepository;
import com.final_project.note.service.dto.GeminiResponse;
import com.final_project.note.service.dto.PythonApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Lazy
@Service
@RequiredArgsConstructor
public class NoteWriteService {

    private final RestTemplate restTemplate;
    private final NoteRepository noteRepository;
    private final MemberUtil memberUtil;
    private final ObjectMapper om;

    public Note createNote(RequestNote form) {

        // 1. MemberUtil을 통해 로그인한 회원 정보를 가져옵니다.
        /*Member member = memberUtil.getMember();

        // 2. 로그인 상태가 아니라면, 예외를 발생시켜 저장을 중단합니다.
        if (member == null) {
            // 더 안전한 코드를 위한 최종 방어선 역할.
            throw new RuntimeException("로그인한 사용자만 노트를 저장할 수 있습니다.");
        }*/

        Member member = new Member();
        member.setMemberId("testUser01");

        Note note = Note.builder()
                .noteId(UUID.randomUUID().toString())
                .memberId(member.getMemberId())
                .youtubeUrl(form.getYoutubeUrl())
                .title(form.getTitle())
                .noteStatus(NoteStatus.PROCESSING)
                .build();

        try{
            final String pythonApiUrl = "http://127.0.0.1:5000/process";

            PythonApiRequest pythonApiRequest = new PythonApiRequest(form.getYoutubeUrl());
            HttpEntity<PythonApiRequest> request = new HttpEntity<>(pythonApiRequest);

            GeminiResponse response = restTemplate.postForObject(pythonApiUrl, request, GeminiResponse.class);

            if (response == null || response.getSummary() == null) {
                throw new RuntimeException("노트 생성에 실패했습니다.");
            }

//            note.setTitle(response.getYoutubeUrl());
            note.setSummary(response.getSummary());
            note.setNoteStatus(NoteStatus.COMPLETED);

            // TagDTO 목록을 Tag 엔티티 목록으로 변환하여 Note에 추가
            if (response.getTags() != null) {
                List<Tag> tags = response.getTags().stream()
                        .map(tagDTO -> Tag.builder()
                                .name(tagDTO.getTag())
                                .description(tagDTO.getDescription())
                                .note(note)
                                .build())
                        .collect(Collectors.toList());
                note.setTags(tags);
            }

            // QuizDTO 목록을 Quiz 엔티티 목록으로 변환하여 Note에 추가
            if (response.getQuiz() != null) {
                List<Quiz> quizzes = response.getQuiz().stream()
                        .map(quizDTO -> {
                            try {
                                // ▼▼▼ 2. options 목록을 JSON 문자열로 변환하는 핵심 로직 ▼▼▼
                                String optionsAsJson = om.writeValueAsString(quizDTO.getOptions());

                                return Quiz.builder()
                                        .question(quizDTO.getQuestion())
                                        .options(optionsAsJson) // JSON 문자열로 저장
                                        .answer(quizDTO.getAnswer())
                                        .note(note)
                                        .build();
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException("퀴즈 옵션을 JSON으로 변환하는 데 실패했습니다.", e);
                            }
                        })
                        .collect(Collectors.toList());
                note.setQuizzes(quizzes);
            }

            if (form.getContents() != null && !form.getContents().isEmpty()) {
                // 1. 전달받은 Content 목록의 각 항목에 Note 엔티티를 설정합니다. (연관관계 매핑)
                form.getContents().forEach(content -> content.setNote(note));
                // 2. Note 엔티티에 Content 목록을 설정합니다.
                note.setContent(form.getContents());
            }

        } catch (Exception e) {
            note.setNoteStatus(NoteStatus.FAILED);
            noteRepository.save(note);
            throw new RuntimeException("노트 생성 실패" + e.getMessage());
        }

        return noteRepository.saveAndFlush(note);

    }
}

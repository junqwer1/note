package com.final_project.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.final_project.note.constants.NoteStatus;
import com.final_project.note.controllers.RequestNote;
import com.final_project.note.entities.Note;
import com.final_project.note.service.NoteWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @MockBean
//    private NoteWriteService noteWriteService;

    @Test
    @WithMockUser // Spring Security 인증을 통과하기 위한 Mock 유저 설정
    @DisplayName("노트 생성 API 통합 테스트")
    void writeTest() throws Exception {
        // Given: 테스트를 위한 요청 데이터와 Mock 서비스 설정
        RequestNote requestNote = new RequestNote();
        requestNote.setTitle("테스트 노트");
        requestNote.setYoutubeUrl("https://www.youtube.com/watch?v=NNClHeXzIBA&list=PLumVmq_uRGHgBrimIp2-7MCnoPUskVMnd");

//        Note returnedNote = new Note();
//        returnedNote.setNoteId("some-uuid");
//        returnedNote.setMemberId("user-id"); // memberId 추가
//        returnedNote.setTitle("테스트 노트");
//        returnedNote.setSummary("AI 요약 내용입니다.");
//        returnedNote.setNoteStatus(NoteStatus.COMPLETED);

//        when(noteWriteService.createNote(any(RequestNote.class))).thenReturn(returnedNote);

        // When & Then: API를 호출하고 결과를 검증
        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestNote)))
                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.data.noteId").value("some-uuid"))
//                .andExpect(jsonPath("$.data.memberId").value("user-id")) // memberId 검증 추가
//                .andExpect(jsonPath("$.data.summary").value("AI 요약 내용입니다."))
//                .andExpect(jsonPath("$.data.noteStatus").value("COMPLETED"))
                .andExpect(jsonPath("$.data.title").value("테스트 노트"))
                .andExpect(jsonPath("$.data.summary", is(notNullValue())))
                .andExpect(jsonPath("$.data.noteStatus").value("COMPLETED"))
                .andDo(print());
    }
}

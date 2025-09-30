package com.final_project.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.final_project.member.Member;
import com.final_project.member.MemberUtil;
import com.final_project.note.constants.NoteStatus;
import com.final_project.note.controllers.RequestNote;
import com.final_project.note.entities.Note;
import com.final_project.note.service.NoteWriteService;
import org.junit.jupiter.api.BeforeEach;
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
    private ObjectMapper om;

    @MockBean // ◀◀◀ 1. MemberUtil을 가짜 객체로 만듭니다.
    private MemberUtil memberUtil;

    @BeforeEach
        // ◀◀◀ 2. 모든 테스트 실행 전에 이 코드가 먼저 실행됩니다.
    void setUp() {
        // "getMember()가 호출되면, 'testUser'라는 ID를 가진 Member 객체를 반환해줘" 라고 설정
        Member mockMember = new Member();
        mockMember.setMemberId("testUser");
        when(memberUtil.getMember()).thenReturn(mockMember);
    }

    @Test
    @WithMockUser // Spring Security 인증을 통과하기 위한 Mock 유저 설정
    @DisplayName("노트 생성 API 통합 테스트")
    void writeTest() throws Exception {
        // Given: 테스트를 위한 요청 데이터와 Mock 서비스 설정
        RequestNote requestNote = new RequestNote();
        requestNote.setTitle("테스트 노트");
        requestNote.setYoutubeUrl("https://www.youtube.com/watch?v=P6AgXuh-fxA&list=PLumVmq_uRGHgBrimIp2-7MCnoPUskVMnd&index=4");

        // When & Then: API를 호출하고 결과를 검증
        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(requestNote)))
                .andDo(print()) // ◀◀◀ 요청과 응답의 모든 내용을 콘솔에 출력하여 확인
                .andExpect(status().isCreated()) // ◀◀◀ 201 Created 상태를 기대
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.noteId").exists()) // noteId가 있는지 확인
                .andExpect(jsonPath("$.data.memberId").exists()) // memberId가 있는지 확인
                .andExpect(jsonPath("$.data.title").value(requestNote.getTitle()))
                // AI가 생성하는 내용은 매번 달라질 수 있으므로, null이 아닌지만 확인
                .andExpect(jsonPath("$.data.summary", is(notNullValue())))
                // quizzes와 tags 목록이 비어있지 않은지만 확인
                .andExpect(jsonPath("$.data.quizzes", is(not(empty()))))
                .andExpect(jsonPath("$.data.tags", is(not(empty()))))
                .andExpect(jsonPath("$.data.noteStatus").value("COMPLETED"));
    }
}

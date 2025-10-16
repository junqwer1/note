package com.final_project.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.final_project.board.controllers.RequestBoard;
import com.final_project.board.repositories.BoardRepository;
import com.final_project.member.Member;
import com.final_project.member.MemberUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper om;

    @MockBean
    private MemberUtil memberUtil;

    @BeforeEach
    void setUp() {
        Member mockMember = new Member();
        mockMember.setMemberId("testUser");
        when(memberUtil.getMember()).thenReturn(mockMember);
    }

    @Test
    void createBoard() throws Exception{
        String noteId = "009f51b3-81d2-4309-81da-240d4aca4609";

        RequestBoard requestBoard = new RequestBoard();
        requestBoard.setNoteId(noteId);

        String jsonRequest = om.writeValueAsString(requestBoard);

        mockMvc.perform(post("/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print());
    }

    @Test
    void listBoard() throws Exception{
        mockMvc.perform(get("/board/list")) // GET /board/list API를 호출
                .andDo(print()) // 요청/응답 전체 내용을 콘솔에 출력
                .andExpect(status().isOk()) // HTTP 상태 코드가 200 OK 인지 검증
                .andExpect(jsonPath("$.success").value(true)) // 응답 JSON의 success 필드가 true인지 검증
                .andExpect(jsonPath("$.data").isArray()); // 'data' 필드가 배열(목록) 형태인지 검증
    }

    @Test
    void viewBoard() throws Exception{
        mockMvc.perform(get("/board/view/" + 502))
                .andDo(print());
    }
}

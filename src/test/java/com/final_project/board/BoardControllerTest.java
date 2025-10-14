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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        String noteId = "156c0f6c-4335-42cf-abcb-3edf11defcc8";

        RequestBoard requestBoard = new RequestBoard();
        requestBoard.setNoteId(noteId);

        String jsonRequest = om.writeValueAsString(requestBoard);

        mockMvc.perform(post("/board/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.title").value("테스트 노트 제목"))
                .andExpect(jsonPath("$.data.author").value("testUser01"));
    }
}

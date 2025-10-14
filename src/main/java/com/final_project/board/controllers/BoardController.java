package com.final_project.board.controllers;

import com.final_project.board.entities.Board;
import com.final_project.board.services.BoardCreateService;
import com.final_project.global.rests.JSONData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardCreateService boardCreateService;

    @PostMapping("/create")
    public ResponseEntity<JSONData> create(@Valid @RequestBody RequestBoard form) {
        try {
            Board board = boardCreateService.boardCreate(form);

            JSONData jsonData = new JSONData(board);

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonData);
        } catch (BadRequestException e) {
            JSONData jsonData = new JSONData();
            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            jsonData.setMessage("게시글 생성 중 오류가 발생했습니다: " + e.getMessage());

            // 실패 응답 (500 Internal Server Error) 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonData);
        }
    }
}

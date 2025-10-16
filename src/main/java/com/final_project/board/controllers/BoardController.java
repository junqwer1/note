package com.final_project.board.controllers;

import com.final_project.board.entities.Board;
import com.final_project.board.services.BoardCreateService;
import com.final_project.board.services.BoardInfoService;
import com.final_project.board.services.dto.BoardDetailResponse;
import com.final_project.global.rests.JSONData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardCreateService createService;
    private final BoardInfoService infoService;

    @PostMapping("/create")
    public ResponseEntity<JSONData> create(@Valid @RequestBody RequestBoard form) {
        try {
            Board board = createService.boardCreate(form);

            JSONData jsonData = new JSONData(board);

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonData);
        } catch (SecurityException e) { // 권한 관련 예외 처리
            JSONData jsonData = new JSONData();
            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.FORBIDDEN); // 403 Forbidden
            jsonData.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(jsonData);

        } catch (IllegalArgumentException e) { // 잘못된 요청 데이터 예외 처리
            JSONData jsonData = new JSONData();
            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.NOT_FOUND); // 404 Not Found
            jsonData.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonData);

        } catch (Exception e) { // 그 외 모든 예외 처리
            JSONData jsonData = new JSONData();
            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR); // 500
            jsonData.setMessage("서버 내부 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonData);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<JSONData> list() {
        try {
            // 서비스 로직을 호출하여 게시글 목록을 가져옴
            List<Board> boardList = infoService.getList();

            // 성공 시: 조회된 목록을 담은 JSONData 객체 생성
            JSONData jsonData = new JSONData(boardList);

            // 성공 응답 (200 OK) 반환
            return ResponseEntity.ok(jsonData);

        } catch (Exception e) {
            // 실패 시: 오류 정보를 담은 JSONData 객체 생성
            JSONData jsonData = new JSONData();
            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            jsonData.setMessage("게시글 목록을 불러오는 중 오류가 발생했습니다: " + e.getMessage());

            // 실패 응답 (500 Internal Server Error) 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonData);
        }
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<JSONData> view(@PathVariable("id") Long boardId) {
        try {
            BoardDetailResponse board = infoService.get(boardId);

            JSONData jsonData = new JSONData(board);

            return ResponseEntity.ok(jsonData);
        } catch (IllegalArgumentException e) {
            JSONData jsonData = new JSONData();

            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.NOT_FOUND);
            jsonData.setMessage(e.getMessage());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(jsonData);

        } catch (Exception e) {
            JSONData jsonData = new JSONData();

            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            jsonData.setMessage("게시글을 조회하는 중 오류가 발생했습니다.");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonData);
        }



    }
}

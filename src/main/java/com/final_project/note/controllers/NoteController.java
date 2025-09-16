package com.final_project.note.controllers;

import com.final_project.global.rests.JSONData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    /*노트 생성*/
    @PostMapping
    public JSONData write(@Valid @RequestBody RequestNote form, Error error, HttpServletRequest request){

    }

    /*노트 상태 조회*/
    @GetMapping("/status")
    public JSONData status(){

    }

    /*노트 상세 조회*/
    @GetMapping("/Detail/{note_id}")
    public JSONData detail(@PathVariable("note_id") String noteId){

    }

    /*노트 제목 수정*/
    @PutMapping
    public JSONData titleUpdate() {

    }

    /*메모 작성 및 수정*/
    @PutMapping("/Detail")
    public JSONData memoUpdate() {

    }

    /*태그 추가 및 수정*/
    @PutMapping("/Detail")
    public JSONData tagUpdate() {

    }

    /*노트 삭제*/
    @DeleteMapping("/deletes")
    public JSONData deletes(@RequestParam("note_id") List<String> note_ids){

    }

    /*퀴즈 풀이 제출*/
    @PostMapping("/quiz/submit")
    public JSONData submit(){
        
    }

    /*퀴즈 목록 조회*/
    @GetMapping("/quiz")
    public JSONData quiz(@ModelAttribute QuizSearch search){

    }
}

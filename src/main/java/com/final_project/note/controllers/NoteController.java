package com.final_project.note.controllers;

import com.final_project.global.configs.BeansConfig;
import com.final_project.global.rests.JSONData;
import com.final_project.note.entities.Note;
import com.final_project.note.service.NoteWriteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NoteController {

    private final NoteWriteService noteWriteService;

    /*노트 생성*/
    @PostMapping
    public ResponseEntity<JSONData> write(@Valid @RequestBody RequestNote form){
        try {
            Note note = noteWriteService.createNote(form);

            JSONData jsonData = new JSONData(note);

            return ResponseEntity.status(HttpStatus.CREATED).body(jsonData);
        } catch (Exception e){
            JSONData jsonData = new JSONData();
            jsonData.setSuccess(false);
            jsonData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            jsonData.setMessage("노트 생성 중 오류가 발생했습니다." + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonData);
        }

    }

    /*노트 상태 조회*/
    /*@GetMapping("/status/{note_id}")
    public JSONData status(){

    }*/

    /*노트 상세 조회*/
    /*@GetMapping("/Detail/{note_id}")
    public JSONData detail(@PathVariable("note_id") String noteId){
//        Note data = infoService.get(noteId);
        return null;
    }*/

    /*노트 제목 수정*//*
    @PutMapping
    public JSONData titleUpdate() {

    }

    *//*메모 작성 및 수정*//*
    @PutMapping("/Detail")
    public JSONData memoUpdate() {

    }

    *//*노트 삭제*//*
    @DeleteMapping("/deletes")
    public JSONData deletes(@RequestParam("note_id") List<String> note_ids){

    }

    *//*퀴즈 풀이 제출*//*
    @PostMapping("/quiz/submit")
    public JSONData submit(){
        
    }

    *//*퀴즈 목록 조회*//*
    @GetMapping("/quiz")
    public JSONData quiz(@ModelAttribute QuizSearch search){

    }*/
}

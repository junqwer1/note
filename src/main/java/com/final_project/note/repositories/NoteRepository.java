package com.final_project.note.repositories;

import com.final_project.note.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, String>, QuerydslPredicateExecutor<Note> {

}

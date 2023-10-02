package com.note.notemaker.repository;

import com.note.notemaker.model.Note;
import com.note.notemaker.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findTop10ByUserOrderByTimestampDesc(UserModel user);
    void deleteByTimestampBefore(LocalDateTime oneHourAgo);
}

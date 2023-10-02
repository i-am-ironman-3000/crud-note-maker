package com.note.notemaker.controller;

import com.note.notemaker.model.Note;
import com.note.notemaker.model.UserModel;
import com.note.notemaker.repository.NoteRepository;
import com.note.notemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public Note createNote(@RequestBody Note noteRequest, Principal p) {
        // Authenticate the user (You should implement proper authentication logic)
        UserModel user = userRepository.findByEmail(p.getName());
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user");
        }

        Note note = new Note();
        note.setContent(noteRequest.getContent());
        note.setTimestamp(LocalDateTime.now());
        note.setUser(user);

        return noteRepository.save(note);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Note>> getRecentNotes(Principal p) {
        // Authenticate the user (You should implement proper authentication logic)
        UserModel user = userRepository.findByEmail(p.getName());
        List<Note> data=noteRepository.findTop10ByUserOrderByTimestampDesc(user);
        return ResponseEntity.ok(data);
    }
    @DeleteMapping("/delete")
    public String deleteById(@RequestParam("id") int id,Principal p){
        System.out.println("deleting");
        noteRepository.deleteById(id);
        System.out.println("deleted");
        return "Deleted successfully";
    }
}



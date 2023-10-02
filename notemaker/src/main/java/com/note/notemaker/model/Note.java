package com.note.notemaker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private LocalDateTime timestamp;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;
}

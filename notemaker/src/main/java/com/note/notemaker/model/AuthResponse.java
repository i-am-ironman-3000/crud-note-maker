package com.note.notemaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.catalina.User;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
}

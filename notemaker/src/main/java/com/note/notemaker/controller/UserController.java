package com.note.notemaker.controller;

import com.note.notemaker.config.CustomUserService;
import com.note.notemaker.model.AuthRequest;
import com.note.notemaker.model.AuthResponse;
import com.note.notemaker.model.UserModel;
import com.note.notemaker.service.JwtService;
import com.note.notemaker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    private CustomUserService service;
    @Autowired
    private UserService userv;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public UserModel addNewUser(@RequestBody UserModel userInfo) {
        return userv.addUser(userInfo);
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }

    @PostMapping("/login")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {

        System.out.println("welcome:"+authRequest.getUsername());
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        String token=jwtService.generateToken(authRequest.getUsername());
        System.out.println(token);
        if(token!=null) return new AuthResponse(token,authRequest.getUsername());
        throw new UsernameNotFoundException("no user found");
    }

}

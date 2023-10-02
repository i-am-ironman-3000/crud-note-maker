package com.note.notemaker.service;

import java.time.LocalDateTime;
import java.util.List;

import com.note.notemaker.model.Note;
import com.note.notemaker.model.UserModel;
import com.note.notemaker.repository.NoteRepository;
import com.note.notemaker.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    UserRepository urepo;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    BCryptPasswordEncoder encode;
    public UserModel addUser(UserModel user) {
        if(findByEmail(user.getEmail())!=null) return null;
        user.setPassword(encode.encode(user.getPassword()));
        return urepo.save(user);
    }
    public UserModel findByEmail(String email) {
        UserModel user=urepo.findByEmail(email);
        return user;
    }
    public List<UserModel> getAllUser(){
        return urepo.findAll();
    }
    @Transactional
    @Scheduled(fixedRate = 60*60*1000)
    public void task(){
        List<UserModel> users=urepo.findAll();
        List<Note> dataToDelete=noteRepository.findAll();

        for (UserModel user : users) {
            List<Note> entitiesToDelete = noteRepository.findTop10ByUserOrderByTimestampDesc(user);
            dataToDelete.removeIf((data)-> data.getUser().getId()==user.getId() && !entitiesToDelete.contains(data));
        }
        noteRepository.deleteAll(dataToDelete);
    }
}

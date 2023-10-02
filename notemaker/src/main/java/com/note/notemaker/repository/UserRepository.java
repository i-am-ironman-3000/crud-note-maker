package com.note.notemaker.repository;

import com.note.notemaker.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>{

    UserModel findByEmail(String email);

}

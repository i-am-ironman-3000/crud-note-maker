package com.note.notemaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    @OneToMany(cascade =CascadeType.ALL,mappedBy = "user")
    private List<Note> notes;
    @Override
    public boolean equals(Object o){
        UserModel user=(UserModel) o;
        return this.id==user.getId();
    }
}

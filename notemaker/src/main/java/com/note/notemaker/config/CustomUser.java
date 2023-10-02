package com.note.notemaker.config;

import java.util.Collection;
import java.util.HashSet;

import com.note.notemaker.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails{
    private UserModel user;
    public CustomUser(UserModel user) {
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        HashSet<SimpleGrantedAuthority> set=new HashSet<>();
        set.add(new SimpleGrantedAuthority(user.getRole()));
        return set;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

}
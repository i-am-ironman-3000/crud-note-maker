package com.note.notemaker.config;

import com.note.notemaker.model.UserModel;
import com.note.notemaker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
@Component
public class CustomUserService implements UserDetailsService{
    @Autowired
    UserRepository urepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserModel u=urepo.findByEmail(username);
            if(u==null) {
                throw new UsernameNotFoundException("user name cannot be found");
            }else {
                //System.out.println(u.toString());
                return new CustomUser(u);
            }
        }catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return null;
    }

}
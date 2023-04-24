package com.website.blog.infra.security;

import com.website.blog.users.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticationService implements UserDetailsService{

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        return user;
    }


}

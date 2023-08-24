package com.Codenera.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Codenera.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    User getCurrentUser();

}

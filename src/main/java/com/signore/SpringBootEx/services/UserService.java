package com.signore.SpringBootEx.services;

import com.signore.SpringBootEx.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findByUsername(String username);

}

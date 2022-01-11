package com.example.myproject.services;

import com.example.myproject.services.models.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserServiceInt extends UserDetailsService {
    void register(UserServiceModel user);
}

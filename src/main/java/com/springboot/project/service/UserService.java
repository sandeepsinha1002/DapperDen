package com.springboot.project.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.springboot.project.model.User;
import com.springboot.project.repository.UserRepository;



@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User createUser( User user)
    {
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    
}

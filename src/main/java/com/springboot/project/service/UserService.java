package com.springboot.project.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.springboot.project.exception.UserException;
import com.springboot.project.model.User;
import com.springboot.project.repository.UserRepository;



@Service
public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
    
}

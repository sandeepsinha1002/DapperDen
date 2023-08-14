package com.springboot.project.service;



import org.springframework.stereotype.Service;

import com.springboot.project.exception.UserException;
import com.springboot.project.model.User;




@Service
public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
    
}

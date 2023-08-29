package com.springboot.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Rating;
import com.springboot.project.model.User;
import com.springboot.project.request.RatingRequest;

@Service
public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductsRating(Long ProducId);
    
}

package com.springboot.project.service;

import java.util.List;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Rating;
import com.springboot.project.model.User;
import com.springboot.project.request.RatingRequest;

public interface RatingService {

    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductsRating(Long ProducId);
    
}

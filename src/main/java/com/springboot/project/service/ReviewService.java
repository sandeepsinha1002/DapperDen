package com.springboot.project.service;

import java.util.List;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Review;
import com.springboot.project.model.User;
import com.springboot.project.request.ReviewRequest;

public interface ReviewService {
    
    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getAllReview(Long ProductId);
}

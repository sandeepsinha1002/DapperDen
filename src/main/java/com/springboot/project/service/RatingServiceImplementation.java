package com.springboot.project.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Product;
import com.springboot.project.model.Rating;
import com.springboot.project.model.User;
import com.springboot.project.repository.RatingRepository;
import com.springboot.project.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService{

    private RatingRepository ratingRepository;
    private ProductService productService;
    

    public RatingServiceImplementation(RatingRepository ratingRepository, ProductService productService) {
        this.ratingRepository = ratingRepository;
        this.productService = productService;
    }

    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product =productService.findProductById(req.getProductId());

        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());

        return ratingRepository.save(rating);


    }

    @Override
    public List<Rating> getProductsRating(Long productId) {

        return ratingRepository.getAllProductsRating(productId);
    }

    
}

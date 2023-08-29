package com.springboot.project.service;

import org.springframework.stereotype.Service;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.User;
import com.springboot.project.request.AddItemRequest;

@Service
public interface CartService {

    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

    public Cart findUserCart(Long userId);
}

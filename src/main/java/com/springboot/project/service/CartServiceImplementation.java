package com.springboot.project.service;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.User;
import com.springboot.project.repository.CartRepository;
import com.springboot.project.request.AddItemRequest;

public class CartServiceImplementation implements CartService{

    private CartRepository cartRepository;
    private CartItemService cartItemService;

    @Override
    public Cart createCart(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCart'");
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCartItem'");
    }

    @Override
    public Cart findUserCart(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserCart'");
    }
    
}

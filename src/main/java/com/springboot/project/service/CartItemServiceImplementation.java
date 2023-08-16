package com.springboot.project.service;

import com.springboot.project.exception.CartItemException;
import com.springboot.project.exception.UserException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Product;
import com.springboot.project.model.Size;

public class CartItemServiceImplementation implements CartItemService {

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCartItem'");
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCartItem'");
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, Size size, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCartItemExist'");
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeCartItem'");
    }

    @Override
    public CartItem findCartItembyID(Long cartItemId) throws CartItemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findCartItembyID'");
    }
    
}

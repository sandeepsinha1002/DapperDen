package com.springboot.project.service;

import com.springboot.project.exception.CartItemException;
import com.springboot.project.exception.UserException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Product;
import com.springboot.project.model.Size;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);
    
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException,UserException;

    public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);

    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException,UserException;

    public CartItem findCartItembyID(Long cartItemId) throws CartItemException;



}

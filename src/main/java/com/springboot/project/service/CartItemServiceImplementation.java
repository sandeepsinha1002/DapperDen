package com.springboot.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.project.exception.CartItemException;
import com.springboot.project.exception.UserException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Product;
import com.springboot.project.model.Size;
import com.springboot.project.model.User;
import com.springboot.project.repository.CartItemRepository;
import com.springboot.project.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;


    

    public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService,
            CartRepository cartRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);

        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item = findCartItembyID(id);
        User user = userService.findUserById(item.getUserId());

        if(user.getId().equals(userId))
        {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        CartItem cartItem =cartItemRepository.isCartItemExist(cart, product, size, userId);
        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

        CartItem cartItem  =findCartItembyID(cartItemId);
        User user =userService.findUserById(cartItem.getUserId());

        User reqUser = userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId()))
        {
            cartItemRepository.deleteById(cartItemId);
        }
        else{
            throw new UserException("You cannot remove other user's item");
        }

        }

    @Override
    public CartItem findCartItembyID(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
        if(opt.isPresent())
        {
            return opt.get();
        }
        else{
            throw new CartItemException("CartItem not found with cartItemId : " + cartItemId);
        }
    }
    
}

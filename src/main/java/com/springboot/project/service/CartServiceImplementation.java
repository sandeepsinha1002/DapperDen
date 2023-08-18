package com.springboot.project.service;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.CartItem;
import com.springboot.project.model.Product;
import com.springboot.project.model.User;
import com.springboot.project.repository.CartRepository;
import com.springboot.project.request.AddItemRequest;

public class CartServiceImplementation implements CartService{

    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService; 

    public CartServiceImplementation(CartRepository cartRepository, CartItemService cartItemService,
            ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemService = cartItemService;
        this.productService = productService;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart, product,req.getSize(), userId);
        if(isPresent == null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			

            int price = req.getQuantity()*product.getPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem=cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}

        return "Item added to Cart";

    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalItem=0;
        for(CartItem cartItems: cart.getCartItems())
        {
            totalPrice=totalPrice+cartItems.getPrice();
            totalItem=totalItem+cartItems.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalItem(totalItem);

        return cartRepository.save(cart);
    }
    
}

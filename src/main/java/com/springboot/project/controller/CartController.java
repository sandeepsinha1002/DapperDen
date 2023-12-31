package com.springboot.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.exception.ProductException;
import com.springboot.project.exception.UserException;
import com.springboot.project.model.Cart;
import com.springboot.project.model.User;
import com.springboot.project.request.AddItemRequest;
import com.springboot.project.response.ApiResponse;
import com.springboot.project.service.CartService;
import com.springboot.project.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private CartService  cartService;
    private UserService userService;
    public CartController(CartService cartService, UserService userService) {
        this.cartService = cartService;
        this.userService = userService;
    }
    @GetMapping("/")
    public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException{
        User user = userService.findUserProfileByJwt(jwt);
        Cart cart=cartService.findUserCart(user.getId());
        System.out.println("cart - "+cart.getUser().getEmail());
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
    }

    @PutMapping("/add")
	public ResponseEntity<ApiResponse> addItemToCart(@RequestBody AddItemRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		cartService.addCartItem(user.getId(), req);
		
		ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);
		
		return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);
    }
		
	
    
}

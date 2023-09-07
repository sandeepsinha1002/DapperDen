package com.springboot.project.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.config.JwtTokenProvider;
import com.springboot.project.exception.UserException;
import com.springboot.project.model.Address;
import com.springboot.project.model.PaymentInformation;
import com.springboot.project.model.User;
import com.springboot.project.repository.UserRepository;
import com.springboot.project.request.LoginRequest;
import com.springboot.project.response.AuthResponse;

import com.springboot.project.service.CustomerUserDetails;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private PasswordEncoder passwordEncoder;
    private CustomerUserDetails customerUserDetails;


    
    public AuthController(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder, CustomerUserDetails customerUserDetails) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.customerUserDetails = customerUserDetails;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException
    {
        String email = user.getEmail();
        String password = user.getPassword();
        String name = user.getName();
        String phoneNumber = user.getPhoneNumber();
        LocalDateTime createdAt = user.getCreatedAt();
        List<Address> address = user.getAddress();
        List<PaymentInformation> paymentInformation=user.getPaymentInformation();


        User isEmailExist = userRepository.findByEmail(email);

        if(isEmailExist!=null)
        {
            throw new UserException("Email is already in use");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setName(name);
        createdUser.setPhoneNumber(phoneNumber);
        createdUser.setAddress(address);
        createdUser.setPaymentInformation(paymentInformation);
        createdUser.setCreatedAt(createdAt);


        User savedUser = userRepository.save(createdUser);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        
	    AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Signup Success");
			
	    return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest )
    {

        String email= loginRequest.getEmail();
        String password = loginRequest.getPassword();


        Authentication authentication = authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        
	    AuthResponse authResponse= new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Success");
			
	    return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
        
    }

    private Authentication authenticate(String email,String password)
    {
        UserDetails userDetails = customerUserDetails.loadUserByUsername(email);

        if(userDetails==null)
        {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    
}

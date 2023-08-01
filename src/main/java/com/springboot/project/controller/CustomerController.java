package com.springboot.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerController {

    @RequestMapping("/")
    public String home(){
        System.out.println("this is home page");
        return "home";

    }
    
    @RequestMapping("/products")
    public String productList() {
    	System.out.println("this is product page");
    	return "product";
    	
    }

    @RequestMapping("/about")
        public String about(){
            System.out.println("This is about page");
            return "about";
        }
    }


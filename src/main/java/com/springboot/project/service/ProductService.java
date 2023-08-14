package com.springboot.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.springboot.project.exception.ProductException;
import com.springboot.project.model.Product;
import com.springboot.project.request.CreateProductRequest;

@Service
public interface ProductService {

    public Product createProduct(CreateProductRequest req) throws ProductException;

    public String deleteProduct(Long prodcutId) throws ProductException;

    public Product updateProduct(Long productId,Product product)throws ProductException;
    
	public List<Product> getAllProducts();


    public Product findProductById(Long id) throws ProductException;

    public List<Product> findProductBycategory(String category);

    public List<Product> searchProduct(String query);

    public Page<Product> getAllProduct(String category, List<String>colors, List<String> sizes, Integer minPrice, Integer maxPrice,String sort, String stock, Integer pageNumber, Integer pageSize);
}

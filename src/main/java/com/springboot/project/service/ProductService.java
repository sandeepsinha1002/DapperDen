package com.springboot.project.service;

import org.springframework.stereotype.Service;

import com.springboot.project.model.Product;
import com.springboot.project.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product)
    {
        return productRepository.save(product);
    }
    public Product getProduct(Long productId)
    {
        return productRepository.findById(productId).orElse(null);
    }
    public Product updateProduct(Long productId, Product updatedProduct)
    {
        Product product = productRepository.findById(productId).orElse(null);
        if(product!=null)
        {
            product.setTitle(updatedProduct.getTitle());
            product.setTitle(updatedProduct.getTitle());
            product.setDescription(updatedProduct.getDescription());
            product.setQuantity(updatedProduct.getQuantity());
            product.setPrice(updatedProduct.getPrice());
            product.setImageUrl(updatedProduct.getImageUrl());
            return productRepository.save(product);
        } 
        return null;
    }
    public boolean deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            productRepository.delete(product);
            return true;
        }
        return false;
    }
    
}

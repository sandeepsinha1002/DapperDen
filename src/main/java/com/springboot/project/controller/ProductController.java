// package com.springboot.project.controller;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.springboot.project.model.Product;
// import com.springboot.project.service.ProductService;

// @RestController
// @RequestMapping("/products")
// public class ProductController {

//     private final ProductService productService;

//     public ProductController(ProductService productService) {
//         this.productService = productService;
//     }

//     @PostMapping
//     public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//         Product createdProduct = productService.createProduct(product);
//         return ResponseEntity.ok(createdProduct);
//     }
//     @GetMapping("/{productId}")
//     public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
//         Product product = productService.getProduct(productId);
//         if (product != null) {
//             return ResponseEntity.ok(product);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @PutMapping("/{productId}")
//     public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
//         Product product = productService.updateProduct(productId, updatedProduct);
//         if (product != null) {
//             return ResponseEntity.ok(product);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

//     @DeleteMapping("/{productId}")
//     public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
//         boolean deleted = productService.deleteProduct(productId);
//         if (deleted) {
//             return ResponseEntity.noContent().build();
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }

// }

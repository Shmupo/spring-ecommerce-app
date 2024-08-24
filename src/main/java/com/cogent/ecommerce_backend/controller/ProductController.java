package com.cogent.ecommerce_backend.controller;

import com.cogent.ecommerce_backend.entity.Product;
import com.cogent.ecommerce_backend.service.Interface.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/categories/")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("{categoryId}/products")
    public ResponseEntity<Product> createProduct(@PathVariable("categoryId") Long categoryId,
                                                 @Valid @RequestBody Product product) {
        Product data = productService.createProduct(categoryId, product);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("{categoryId}/products")
    public ResponseEntity<List<Product>> getAllCategoryProducts(@PathVariable("categoryId") Long categoryId) {
        List<Product> data = productService.getProductsByCategoryId(categoryId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("categoryId") Long categoryId,
                                                  @PathVariable("productId") Long productId) {
        Product data = productService.getProductById(categoryId, productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> data = productService.getAllProducts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("categoryId") Long categoryId,
                                                 @PathVariable("productId") Long productId,
                                                 @Valid @RequestBody Product product) {
        Product data = productService.updateProduct(categoryId, productId, product);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/products/{productId}")
    public HttpStatus deleteProduct(@PathVariable("categoryId") Long categoryId,
                                    @PathVariable("productId") Long productId) {
        productService.deleteProduct(categoryId, productId);
        return HttpStatus.OK;
    }
}

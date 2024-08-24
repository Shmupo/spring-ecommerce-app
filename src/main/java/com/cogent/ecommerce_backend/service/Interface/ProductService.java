package com.cogent.ecommerce_backend.service.Interface;

import com.cogent.ecommerce_backend.entity.Category;
import com.cogent.ecommerce_backend.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long categoryId, Long productId);
    Product createProduct(Long categoryId, Product product);
    Product updateProduct(Long categoryId, Long productId, Product product);
    List<Product> getProductsByCategoryId(Long categoryId);
    void deleteProduct(Long categoryId, Long productId);
}

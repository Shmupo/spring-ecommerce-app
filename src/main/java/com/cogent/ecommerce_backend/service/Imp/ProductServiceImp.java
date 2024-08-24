package com.cogent.ecommerce_backend.service.Imp;

import com.cogent.ecommerce_backend.entity.Category;
import com.cogent.ecommerce_backend.entity.Product;
import com.cogent.ecommerce_backend.exception.ResourceNotFoundException;
import com.cogent.ecommerce_backend.payload.EcommerceAPIException;
import com.cogent.ecommerce_backend.repository.CategoryRepository;
import com.cogent.ecommerce_backend.repository.ProductRepository;
import com.cogent.ecommerce_backend.service.Interface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long categoryId, Long productId) {
        Category foundCategory = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        Product foundProduct = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

        if (!foundProduct.getCategory().getId().equals(foundCategory.getId())) {
            throw new EcommerceAPIException(HttpStatus.BAD_REQUEST, "Product does not belong to category.");
        }

        return foundProduct;
    }

    @Override
    public Product createProduct(Long categoryId, Product product) {
        Category foundCategory = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));
        product.setCategory(foundCategory);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long categoryId, Long productId, Product product) {
        Category foundCategory = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        Product foundProduct = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

        if (!foundProduct.getCategory().getId().equals(foundCategory.getId())) {
            throw new EcommerceAPIException(HttpStatus.BAD_REQUEST, "Product does not belong to category.");
        }

        foundProduct.setTitle(product.getTitle());
        foundProduct.setDescription(product.getDescription());
        foundProduct.setPrice(product.getPrice());
        foundProduct.setImages(foundProduct.getImages());
        foundProduct.setCategory(foundCategory);

        return productRepository.save(foundProduct);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    @Override
    public void deleteProduct(Long categoryId, Long productId) {
        Category foundCategory = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("category", "categoryId", categoryId));

        Product foundProduct = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("product", "productId", productId));

        if (!foundProduct.getCategory().getId().equals(foundCategory.getId())) {
            throw new EcommerceAPIException(HttpStatus.BAD_REQUEST, "Product does not belong to category.");
        }

        productRepository.delete(foundProduct);

        categoryRepository.save(foundCategory);
    }
}

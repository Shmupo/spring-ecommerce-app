package com.cogent.ecommerce_backend.service.Interface;

import com.cogent.ecommerce_backend.entity.Category;
import com.cogent.ecommerce_backend.payload.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir);
    Category getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategory(Long id);
}

package com.cogent.ecommerce_backend.controller;

import com.cogent.ecommerce_backend.entity.Category;
import com.cogent.ecommerce_backend.payload.CategoryResponse;
import com.cogent.ecommerce_backend.service.Interface.CategoryService;
import com.cogent.ecommerce_backend.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
        CategoryResponse data = categoryService.getAllCategories(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        Category data = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category categoryId) {
        Category data = categoryService.createCategory(categoryId);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Long categoryId, @Valid @RequestBody Category category) {
        Category data = categoryService.updateCategory(categoryId, category);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public HttpStatus deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return HttpStatus.OK;
    }
}

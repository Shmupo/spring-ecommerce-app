package com.cogent.ecommerce_backend.service.Imp;

import com.cogent.ecommerce_backend.entity.Category;
import com.cogent.ecommerce_backend.exception.ResourceNotFoundException;
import com.cogent.ecommerce_backend.payload.CategoryResponse;
import com.cogent.ecommerce_backend.repository.CategoryRepository;
import com.cogent.ecommerce_backend.repository.ProductRepository;
import com.cogent.ecommerce_backend.service.Interface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public CategoryResponse getAllCategories(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Category> categories = categoryRepository.findAll(pageable);

        List<Category> categoryList = categories.getContent();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryList);
        categoryResponse.setPageNo(categories.getNumber());
        categoryResponse.setPageSize(categories.getSize());
        categoryResponse.setTotalPages(categories.getTotalPages());
        categoryResponse.setTotalElements(categories.getTotalElements());
        categoryResponse.setLast(categories.isLast());
        return categoryResponse;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("category", "categoryId", id));
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("category", "categoryId", id));;
        foundCategory.setName(category.getName());
        foundCategory.setImage(category.getImage());
        return categoryRepository.save(foundCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("category", "categoryId", id));;
        categoryRepository.delete(foundCategory);
    }
}

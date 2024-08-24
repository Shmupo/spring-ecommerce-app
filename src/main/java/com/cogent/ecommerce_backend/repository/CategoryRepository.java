package com.cogent.ecommerce_backend.repository;

import com.cogent.ecommerce_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

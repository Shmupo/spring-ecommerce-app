package com.cogent.ecommerce_backend.payload;

import com.cogent.ecommerce_backend.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponse {
    private List<Category> content;
    private int pageNo;
    private int pageSize;
    private long totalPages;
    private long totalElements;
    private boolean last;
}

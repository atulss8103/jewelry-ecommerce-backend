package com.atul.jewelry.category.service;

import com.atul.jewelry.category.dto.request.CreateCategoryRequest;
import com.atul.jewelry.category.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CreateCategoryRequest request);

    List<CategoryResponse> getAllCategories();

}
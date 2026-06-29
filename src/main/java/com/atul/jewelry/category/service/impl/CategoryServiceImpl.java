package com.atul.jewelry.category.service.impl;

import com.atul.jewelry.category.dto.request.CreateCategoryRequest;
import com.atul.jewelry.category.dto.response.CategoryResponse;
import com.atul.jewelry.category.entity.Category;
import com.atul.jewelry.category.repository.CategoryRepository;
import com.atul.jewelry.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse createCategory(CreateCategoryRequest request) {

        if(categoryRepository.existsByName(request.getName())){
            throw new RuntimeException("Category already exists");
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        categoryRepository.save(category);

        return CategoryResponse.builder()
                .id(category.getId().toString())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {

        return categoryRepository.findAll()
                .stream()
                .map(category -> CategoryResponse.builder()
                        .id(category.getId().toString())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build())
                .toList();
    }
}
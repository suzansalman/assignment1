package com.example.assignment1.service;



import com.example.assignment1.dto.CategoryDto;

import java.util.List;


public interface CategoryService {
    CategoryDto createCategory(CategoryDto CategoryDto);

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryById(long id);

    CategoryDto updateCategory(CategoryDto CategoryDto, long id);

    void deleteCategoryById(long id);
}

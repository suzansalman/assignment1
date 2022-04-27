package com.example.assignment1.controller;

import com.example.assignment1.dto.CategoryDto;
import com.example.assignment1.exception.BadRequestException;
import com.example.assignment1.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/category")
public class CategoryResource {
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private CategoryService categoryService; //the use of interface rather than class is important for loose coupling

// Constructor based  injection
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            log.error("No ID {}", categoryDto);
            throw new BadRequestException(CategoryResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, id), HttpStatus.OK);
    }

    //maping
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.deleteCategoryById(id);
//        return ResponseEntity
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}

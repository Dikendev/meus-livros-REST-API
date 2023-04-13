package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/getall")
    public Iterable<Category> getAllCategory(){
        return service.findAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") UUID id) {
        return service.findCategoryById(id);
    }

    @PostMapping
    public Category addCategory(@Valid @RequestBody Category category) {
        return service.addCategory(category);
    }

}

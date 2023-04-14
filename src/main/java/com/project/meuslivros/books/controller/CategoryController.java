package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable("id") UUID id,
                               @Valid @RequestBody Category category) {
        if (!id.equals(category.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match."
        );

        service.updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCategory(
            @PathVariable("id") UUID id) {
        try {
            service.deleteCategory(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/categories")
@CrossOrigin(allowedHeaders = "Content-type")
@PreAuthorize("isAuthenticated()")
public class CategoryController {

    private final CategoryService service;
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);



    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getall")
    public Iterable<Category> getAllCategory(Pageable pageable){
        int toSkip = pageable.getPageSize() * pageable.getPageNumber();

        LOGGER.info("Using SLF4J: Getting category list - getAllCategory()");

        var categoryList = StreamSupport.stream(service.findAllCategory().spliterator(),false)
                .skip(toSkip).limit(pageable.getPageSize())
                .toList();

        log.info("Using SLF4J Lombok: Getting category list - getAllCategory()");

        return categoryList.stream().map(this::addCategory)
                .collect(Collectors.toList());
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

package com.project.meuslivros.books.service;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.repository.CategoryRepository;
import com.project.meuslivros.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Iterable<Category> findAllCategory() {
        return repository.findAll();
    }

    public Category findCategoryById(UUID id) {
        return findOrThrow(id);
    }

    public Category addCategory(Category category) {
        return repository.save(category);
    }

    public void updateCategory(UUID id, Category category) {
        findOrThrow(id);
        repository.save(category);
    }

    public void deleteCategory(UUID id) {
        findOrThrow(id);
        repository.deleteById(id);
    }

    public Category findOrThrow(final UUID id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Category by id " +
                                id + " was not found")
                );
    }
}

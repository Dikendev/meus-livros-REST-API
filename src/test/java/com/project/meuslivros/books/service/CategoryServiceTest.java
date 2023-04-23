package com.project.meuslivros.books.service;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryService serviceTest;

    Category category = new Category();

    @BeforeEach
    void setup() {
        category.setCategoryName("Diego");
        category.setBooks(null);
        category.setId(UUID.randomUUID());
    }

    @Test
    void canFindAllCategories() {

        serviceTest.findAllCategory();

        verify(repository,times(1)).findAll();
    }

    @Test
    void canFindCategoryById() {

        when(repository.findById(category.getId())).thenReturn(Optional.of(category));
        Category capturedCategory = serviceTest.findCategoryById(category.getId());

        assertThat(capturedCategory).isNotNull();
        assertThat(capturedCategory).isEqualTo(category);
        assertThat(capturedCategory.getId()).isEqualTo(category.getId());

        verify(repository, times(1)).findById(category.getId());
    }

    @Test
    void canAddCategory() {
        serviceTest.addCategory(category);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);

        verify(repository, times(1)).save(categoryArgumentCaptor.capture());
        Category captureCategory = categoryArgumentCaptor.getValue();

        assertThat(captureCategory).isEqualTo(category);
    }



}

package com.project.meuslivros.books.service;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;
    @InjectMocks
    private CategoryService serviceTest;

    @Test
    void canFindAllCategories() {

        serviceTest.findAllCategory();

        verify(repository).findAll();
    }

    @Test
    void canAddCategory() {
        Category category = new Category("Diego", null);

        serviceTest.addCategory(category);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);

        verify(repository).save(categoryArgumentCaptor.capture());
        Category captureCategory = categoryArgumentCaptor.getValue();

        assertThat(captureCategory).isEqualTo(category);
    }



}

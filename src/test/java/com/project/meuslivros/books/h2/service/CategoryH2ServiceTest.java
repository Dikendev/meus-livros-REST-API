package com.project.meuslivros.books.h2.service;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.repository.CategoryRepository;
import com.project.meuslivros.books.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CategoryH2ServiceTest {

    @Autowired
    private CategoryRepository repository;
    private CategoryService service;

    @BeforeEach
    public void setup() {
        service = new CategoryService(repository);
    }

    @Test
    public void shouldFindAllCategory() {
        Category category = new Category();
        category.setCategoryName("terror");

        service.addCategory(category);

        Iterable<Category> categoryList = service.findAllCategory();

        Category savedCategory = categoryList.iterator().next();

        assertThat(savedCategory).isNotNull();
    }
}

package com.project.meuslivros.books.h2Service;

import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.repository.CategoryRepository;
import com.project.meuslivros.books.service.CategoryService;
import com.project.meuslivros.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class CategoryH2ServiceTest {

    @Autowired
    private CategoryRepository repository;
    private CategoryService service;

    Category category = new Category();

    @BeforeEach
    public void setup() {
        category.setCategoryName("terror");

        service = new CategoryService(repository);
    }

    @Test
    public void shouldFindAllCategory() {
        service.addCategory(category);

        Iterable<Category> categoryList = service.findAllCategory();
        Category savedCategory = categoryList.iterator().next();

        assertThat(savedCategory).isNotNull();
    }

    @Test
    public void shouldAddCategory() {
        service.addCategory(category);

        Iterable<Category> list = service.findAllCategory();
        Category savedCategory = list.iterator().next();

        assertThat(category).isEqualTo(savedCategory);
    }

    @Test
    public void shouldDeleteCategory() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
               Category savedCategory = service.addCategory(category);

               service.deleteCategory(savedCategory.getId());
               Category foundCategory = service.findCategoryById(savedCategory.getId());

               assertThat(foundCategory).isNotNull();
            }
        });
    }

    @Test
    public void shouldUpdateCategory() {
        Category savedCategory = service.addCategory(category);
        savedCategory.setCategoryName("Suspense");

        service.updateCategory(savedCategory.getId(), savedCategory);
        Category foundCategory = service.findCategoryById(savedCategory.getId());

        assertThat(foundCategory.getCategoryName()).isEqualTo("Suspense");
    }

    @Test
    public void shouldFindCategoryById() {
        Category savedCategory = service.addCategory(category);

        Category foundCategory = service.findCategoryById(savedCategory.getId());

        assertThat(foundCategory.getId()).isNotNull();
    }

    @Test
    public void shouldNotFindCategoryById() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Category foundCategory = service.findCategoryById(UUID.randomUUID());

                assertThat(foundCategory).isNotNull();
            }
        });
    }

}

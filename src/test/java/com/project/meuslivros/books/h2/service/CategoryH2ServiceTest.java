package com.project.meuslivros.books.h2.service;

import com.project.meuslivros.books.repository.CategoryRepository;
import com.project.meuslivros.books.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class CategoryH2ServiceTest {

    @Autowired
    private CategoryRepository repository;
    private CategoryService service;

}

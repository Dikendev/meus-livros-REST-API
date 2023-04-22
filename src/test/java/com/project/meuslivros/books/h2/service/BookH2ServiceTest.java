package com.project.meuslivros.books.h2.service;

import com.project.meuslivros.books.entity.Book;
import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.repository.BookRepository;
import com.project.meuslivros.books.repository.CategoryRepository;
import com.project.meuslivros.books.repository.LanguageRepository;
import com.project.meuslivros.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class BookH2ServiceTest {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LanguageRepository languageRepository;

    private BookService service;

    Book book = new Book();
    Category category = new Category();
    Language language = new Language();


}

package com.project.meuslivros.books.h2.service;

import com.project.meuslivros.books.entity.Book;
import com.project.meuslivros.books.entity.Category;
import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.repository.BookRepository;
import com.project.meuslivros.books.repository.CategoryRepository;
import com.project.meuslivros.books.repository.LanguageRepository;
import com.project.meuslivros.books.service.BookService;
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

    @BeforeEach
    public void setup() {
        language.setLanguageName("English");
        category.setCategoryName("Horror");

        languageRepository.save(language);
        categoryRepository.save(category);

        book.setTitle("O Iluminado");
        book.setSubTitle("REDRUM");
        book.setCategory(category);
        book.setLanguage(language);

        service = new BookService(repository);
    }

    @Test
    public void shouldFindAllBooks() {
        service.addBook(book);

        Iterable<Book> bookList = service.findAllBooks();

        Book savedBook = bookList.iterator().next();

        assertThat(savedBook).isNotNull();
    }

    @Test
    public void shouldAddBook() {
        service.addBook(book);

        Iterable<Book> bookList = service.findAllBooks();
        Book savedBook = bookList.iterator().next();

        assertThat(book).isEqualTo(savedBook);
    }

    @Test
    public void shouldUpdateBook() {
        Book savedBook = service.addBook(book);
        savedBook.setLanguage(new Language("English", null));
        savedBook.setCategory(new Category("Programming", null));
        savedBook.setTitle("Head First Java");
        savedBook.setSubTitle("Java tutorial");

        service.updateBook(savedBook.getId(),savedBook);
        Book foundBook = service.findByBookId(savedBook.getId());

        assertThat(foundBook.getCategory()).isEqualTo(savedBook.getCategory());
        assertThat(foundBook.getLanguage()).isEqualTo(savedBook.getLanguage());
        assertThat(foundBook.getTitle()).isEqualTo("Head First Java");
        assertThat(foundBook.getSubTitle()).isEqualTo("Java tutorial");
    }

    @Test
    public void shouldDeleteBook() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Book savedBook = service.addBook(book);

                service.deleteBook(savedBook.getId());
                Book foundBook = service.findByBookId(savedBook.getId());

                assertThat(foundBook).isNotNull();
            }
        });
    }

    @Test
    public void shouldFindBookById() {
        Book savedBook = service.addBook(book);

        Book foundBook = service.findByBookId(savedBook.getId());

        assertThat(foundBook.getId()).isNotNull();
    }

    @Test
    public void shouldNotFindBookById() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Book foundBook = service.findByBookId(UUID.randomUUID());

                assertThat(foundBook).isNotNull();
            }
        });
    }

}

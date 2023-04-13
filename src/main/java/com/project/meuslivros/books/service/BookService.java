package com.project.meuslivros.books.service;

import com.project.meuslivros.books.entity.Book;
import com.project.meuslivros.books.repository.BookRepository;
import com.project.meuslivros.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Iterable<Book> findAllBooks() {
        return repository.findAll();
    }

    public Book findByBookId(UUID id) {
        return findOrThrow(id);
    }

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public void updateBook(UUID id, Book book) {
        repository.save(book);
    }

    public Book findOrThrow(final UUID id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Book by id " +
                                id + " was not found")
                );
    }
}
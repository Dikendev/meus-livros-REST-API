package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Book;
import com.project.meuslivros.books.DTOs.BookDto;
import com.project.meuslivros.books.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/books")
public class BookController {

    private final BookService service;
    private final ModelMapper mapper;

    @GetMapping("/getall")
    public List<BookDto> getBook() {
        var bookList = StreamSupport
                .stream(service.findAllBooks()
                        .spliterator(),
                        false)
                .collect(Collectors.toList());
        return bookList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable("id") UUID id) {
        return convertToDto(service.findByBookId(id));
    }

    @PostMapping
    public BookDto addBook(@Valid @RequestBody BookDto bookDto) {
        var entity = convertToEntity(bookDto);
        var book = service.addBook(entity);

        return convertToDto(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable("id") UUID id, @Valid @RequestBody BookDto bookDto) {
        if (!id.equals(bookDto.getId())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        var book = convertToEntity(bookDto);
        service.updateBook(id,book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteBook(@PathVariable("id" ) UUID id) {
        try {
            service.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private BookDto convertToDto(Book entity) {
        return mapper.map(entity, BookDto.class);
    }

    private Book convertToEntity(BookDto dto) {
        return mapper.map(dto, Book.class);
    }

}

package com.project.meuslivros.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "author")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "author_id")
    private String authorName;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;
}

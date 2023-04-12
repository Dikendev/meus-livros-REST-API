package com.project.meuslivros.books.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String languageName;
    @OneToMany(mappedBy = "BookLanguage")
    private List<Book> books;
}

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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String categoryName;
    @OneToMany(mappedBy = "category")
    private List<Book> books;
}

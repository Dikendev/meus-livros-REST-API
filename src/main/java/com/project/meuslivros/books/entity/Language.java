package com.project.meuslivros.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.meuslivros.books.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "languages")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
public class Language extends BaseEntity {

    private String languageName;

    @OneToMany(mappedBy = "language")
    private List<Book> books;
}

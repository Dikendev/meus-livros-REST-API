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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","books"})
public class Category extends BaseEntity {

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Book> books;
}

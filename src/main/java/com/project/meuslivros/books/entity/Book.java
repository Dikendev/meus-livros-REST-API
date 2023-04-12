package com.project.meuslivros.books.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank(message = "Title is required")
    private String title;
    private String subTitle;
    @NotBlank(message = "Author is required")
    private String author;
    private String publishedBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private BookLanguage language;
    private Date publishedDate;
    private String edition;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String bookImageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    private String createAt = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm:ss z")
            .format(new Date());

}

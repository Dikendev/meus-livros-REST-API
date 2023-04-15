package com.project.meuslivros.books.entity;

import com.project.meuslivros.books.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Data
@Table(name="book")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    @NotBlank(message = "Title is required")
    private String title;
    private String subTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "publisher_id")
//    private Publisher publisher;

    private String createAt = new SimpleDateFormat(
            "dd-MM-yyyy HH:mm:ss z")
            .format(new Date());

}

package com.project.meuslivros.books.DTOs;

import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private UUID Id;
    private UUID categoryId;
    @JoinColumn(name = "language_id")
    private UUID LanguageId;

    @NotBlank(message = "Title is required")
    private String title;
    private String subTitle;

}

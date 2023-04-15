package com.project.meuslivros.books.DTOs;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private UUID Id;
    private UUID categoryId;
    @JoinColumn(name = "language_id")
    private UUID languageId;

    @NotBlank(message = "Title is required")
    private String title;
    private String subTitle;

}

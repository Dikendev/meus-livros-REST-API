package com.project.meuslivros.books.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;
import java.util.UUID;

@RedisHash("Categories")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","books"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator = "UUID")
    @Column(name = "category_id" ,nullable = false, updatable = false)
    private UUID id;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Book> books;
}

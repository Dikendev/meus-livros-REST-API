package com.project.meuslivros.books.repository;

import com.project.meuslivros.books.entity.BookLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookLanguageRepository extends JpaRepository<BookLanguage, UUID> {

}

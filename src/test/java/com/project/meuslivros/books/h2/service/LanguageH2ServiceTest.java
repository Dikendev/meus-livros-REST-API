package com.project.meuslivros.books.h2.service;

import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.repository.LanguageRepository;
import com.project.meuslivros.books.service.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class LanguageH2ServiceTest {

    @Autowired
    private LanguageRepository repository;
    private LanguageService service;

    Language language = new Language();

    @BeforeEach
    public void setup() {
        language.setLanguageName("English");

        service = new LanguageService(repository);
    }

}

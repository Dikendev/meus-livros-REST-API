package com.project.meuslivros.books.h2Service;

import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.repository.LanguageRepository;
import com.project.meuslivros.books.service.LanguageService;
import com.project.meuslivros.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void shouldFindAllLanguages() {
        service.addLanguage(language);

        Iterable<Language> languageList = service.findAllLanguage();
        Language savedLanguage = languageList.iterator().next();

        assertThat(savedLanguage).isNotNull();
    }

    @Test
    public void shouldAddLanguage() {
        service.addLanguage(language);

        Iterable<Language> languageList = service.findAllLanguage();
        Language savedLanguage = languageList.iterator().next();

        assertThat(language).isEqualTo(savedLanguage);
    }

    @Test
    public void shouldUpdateLanguage() {
        Language savedLanguage = service.addLanguage(language);
        savedLanguage.setLanguageName("Portuguese");

        service.updateLanguage(savedLanguage.getId(), savedLanguage);
        Language foundLanguage = service.findLanguageById(savedLanguage.getId());

        assertThat(foundLanguage.getLanguageName()).isEqualTo("Portuguese");
    }

    @Test
    public void shouldDeleteLanguage() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Language savedLanguage = service.addLanguage(language);

                service.deleteLanguage(savedLanguage.getId());
                Language foundLanguage = service.findLanguageById(savedLanguage.getId());

                assertThat(foundLanguage).isNotNull();
            }
        });
    }

    @Test
    public void shouldFindLanguageById() {
        Language savedLanguage = service.addLanguage(language);

        Language foundLanguage = service.findLanguageById(savedLanguage.getId());

        assertThat(foundLanguage.getId()).isNotNull();
    }

    @Test
    public void shouldNotFindLanguageById() {
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Language foundLanguage = service.findLanguageById(UUID.randomUUID());

                assertThat(foundLanguage).isNotNull();
            }
        });
    }
}

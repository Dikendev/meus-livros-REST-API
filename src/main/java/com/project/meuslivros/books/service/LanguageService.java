package com.project.meuslivros.books.service;

import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.repository.LanguageRepository;
import com.project.meuslivros.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class LanguageService {

    private final LanguageRepository repository;

    public Iterable<Language> findAllLanguage() {
        return repository.findAll();
    }

    public Language findLanguageById(UUID id) {
       return findOrThrow(id);
    }

    public Language addLanguage(Language language) {
        return repository.save(language);
    }

    public void updateLanguage(UUID id, Language language) {
        findOrThrow(id);
        repository.save(language);
    }

    public void deleteLanguage(UUID id) {
        findOrThrow(id);
        repository.deleteById(id);
    }

    public Language findOrThrow(final UUID id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Language by id " +
                                id + " was not found")
                );
    }
}

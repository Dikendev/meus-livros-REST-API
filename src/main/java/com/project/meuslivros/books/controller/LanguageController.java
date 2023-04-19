package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.service.LanguageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/language")
@CrossOrigin(allowedHeaders = "Content-type")
@PreAuthorize("isAuthenticated()")
public class LanguageController {

    private final LanguageService service;

    @GetMapping("/getall")
    public Iterable<Language> getAllLanguage(){
        return service.findAllLanguage();
    }

    @GetMapping("/{id}")
    public Language getLanguageById(@PathVariable("id") UUID id) {
        return service.findLanguageById(id);
    }

    @PostMapping
    public Language addLanguage(@Valid @RequestBody Language language) {
        return service.addLanguage(language);
    }

    @PutMapping("/{id}")
    public void updateLanguage(@PathVariable("id") UUID id,
                                   @Valid @RequestBody Language language) {
        if (!id.equals(language.getId())) throw new
                ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "id does not match"
        );

        service.updateLanguage(id,language);
    }

    @DeleteMapping("/{id}")
    public void deleteLanguage(@PathVariable("id") UUID id) {
        service.deleteLanguage(id);
    }

}

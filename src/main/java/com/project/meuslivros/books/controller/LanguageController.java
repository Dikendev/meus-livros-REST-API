package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.service.LanguageService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;


@RestController
@AllArgsConstructor
@RequestMapping("api/v1/language")
@CrossOrigin(origins = "*", allowedHeaders = "*")
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

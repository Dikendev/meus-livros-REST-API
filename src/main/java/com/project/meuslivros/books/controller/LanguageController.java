package com.project.meuslivros.books.controller;

import com.project.meuslivros.books.entity.Language;
import com.project.meuslivros.books.service.LanguageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}

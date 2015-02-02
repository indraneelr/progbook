package com.progbook.service;

import com.progbook.persistence.model.Language;

import java.util.Set;

public interface LanguageService {
    Language fetchById(String id);
    Set<Language> fetchAll();
    Set<Language> fetchByAnswer(String answerId);

    void save(Language language);
    void delete(String id);

}

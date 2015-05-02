package com.progbook.service;

import com.progbook.persistence.model.Language;

import java.util.List;
import java.util.Set;

public interface LanguageService {
    Language fetchByUuid(String id);
    List<Language> fetchAll();
    Set<Language> fetchByAnswer(String answerId);

    void save(Language language);
    void delete(String id);

    List<Language> fetchByNames(List<String> languageNames);
}

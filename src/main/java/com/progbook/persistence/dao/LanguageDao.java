package com.progbook.persistence.dao;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;

import java.util.List;

public interface LanguageDao {
    void save(Language language);
    Language fetch(long id);
    Language fetch(String uuid);
    List<Language> fetchAll();

    List<Language> fetchByNames(List<String> name);

    void delete(Answer answer);
}

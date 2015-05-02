package com.progbook.persistence.dao;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;

import java.util.List;

public interface LanguageDao extends AbstractDao<Language> {
    List<Language> fetchByNames(List<String> name);
}

package com.progbook.persistence.dao;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;

import java.util.List;

public interface AnswerDao {
    void save(Answer answer);
    Answer fetch(long id);
    List<Answer> fetchAll();

    List<Answer> fetchByQuestionAndLanguages(Question question, List<Language> languages);

    void delete(Answer answer);
}

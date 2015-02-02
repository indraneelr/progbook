package com.progbook.persistence.dao;

import com.progbook.persistence.model.Answer;

import java.util.List;

public interface AnswerDao {
    void save(Answer answer);
    Answer fetch(long id);
    List<Answer> fetchAll();
    void delete(Answer answer);
}

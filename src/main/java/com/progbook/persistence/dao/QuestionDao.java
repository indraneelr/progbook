package com.progbook.persistence.dao;

import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;

import java.util.List;

/**
 * Created by neel on 26-12-2014.
 */
public interface QuestionDao {
    void save(Question question);
    Question fetch(long id);
    List<Question> fetchAll();

    List<Question> filterByTitle(String title);

    List<Question> fetchByTags(List<QuestionTag> questionTags);

    void delete(Question question);

    Question fetch(String uuid);
}

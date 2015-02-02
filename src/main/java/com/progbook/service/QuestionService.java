package com.progbook.service;

import com.progbook.persistence.model.Question;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    Question fetchByTitle(String title);
    Set<Question> fetchByCategory(String category);
    Set<Question> fetchByTags(List<String> tags);
    Question fetchById(String id);

    void save(Question question);
    void delete(String id);

    Integer getAnswerCountFor(String questionId);
}

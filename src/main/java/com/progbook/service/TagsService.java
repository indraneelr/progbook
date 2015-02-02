package com.progbook.service;

import com.progbook.persistence.model.QuestionTag;

import java.util.List;
import java.util.Set;

public interface TagsService {
    QuestionTag fetchById(long id);
    List<QuestionTag> fetchAll();
    List<QuestionTag> fetchByQuestion(String questionId);
    List<QuestionTag> fetchByNames(List<String> tags);

    void save(QuestionTag tag);
    void delete(String id);
}

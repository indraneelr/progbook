package com.progbook.persistence.dao;

import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;

import java.util.List;

/**
 * Created by neel on 26-12-2014.
 */
public interface QuestionDao extends AbstractDao<Question> {
    List<Question> filterByTitle(String title);
    List<Question> fetchByTags(List<QuestionTag> questionTags);

    Integer getAnswerCount(String questionUuid);
}

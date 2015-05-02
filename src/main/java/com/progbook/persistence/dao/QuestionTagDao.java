package com.progbook.persistence.dao;

import com.progbook.persistence.model.QuestionTag;

import java.util.List;
import java.util.Set;

public interface QuestionTagDao extends AbstractDao<QuestionTag> {
    List<QuestionTag> fetchByNames(List<String> names);
}

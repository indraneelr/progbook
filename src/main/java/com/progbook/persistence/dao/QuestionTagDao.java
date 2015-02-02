package com.progbook.persistence.dao;

import com.progbook.persistence.model.QuestionTag;

import java.util.List;
import java.util.Set;

public interface QuestionTagDao {

    void save(QuestionTag questionTag);

    QuestionTag fetch(long id);

    List<QuestionTag> fetchAll();

    List<QuestionTag> fetchByNames(List<String> names);

    void delete(QuestionTag question);
}

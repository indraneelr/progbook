package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.AnswerDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

@Repository
public class AnswerDaoImpl implements AnswerDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Answer answer) {
        entityManager.persist(answer);
    }

    @Override
    public Answer fetch(long id) {
        return entityManager.find(Answer.class,id);
    }

    @Override
    public List<Answer> fetchAll() {
        return entityManager.createQuery("Select a from Answer a",Answer.class).getResultList();
    }

    @Override
    public List<Answer> fetchByQuestionAndLanguages(Question question,List<Language> languages){
        final String LANGUAGE_CLAUSE = " and a.language in :languages";
        final String QUERY_BY_QUESTION = "select a from Answer a where a.question = :question";
        if(CollectionUtils.isEmpty(languages)){
            return entityManager.createQuery(QUERY_BY_QUESTION).setParameter("question",question).getResultList();
        }
        return entityManager.createQuery(QUERY_BY_QUESTION+LANGUAGE_CLAUSE)
                .setParameter("question",question)
                .setParameter("languages",languages)
                .getResultList();
    }
    @Override
    public void delete(Answer answer) {

    }
}

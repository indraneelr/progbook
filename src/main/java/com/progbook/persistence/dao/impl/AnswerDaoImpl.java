package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.AnswerDao;
import com.progbook.persistence.model.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public void delete(Answer answer) {

    }
}

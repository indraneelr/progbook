package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.QuestionDao;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Question question) {
        entityManager.persist(question);
    }

    @Override
    public Question fetch(long id) {
        return entityManager.find(Question.class,id);
    }

    @Override
    public List<Question> fetchAll() {
        return entityManager.createQuery("select q from Question q",Question.class).getResultList();
    }

    @Override
    public List<Question> filterByTitle(String title){
        TypedQuery<Question> query = entityManager.createQuery("select q from Question q where q.title like :title", Question.class);
        query.setParameter("title",'%'+title+'%');
        return query.getResultList();
    }

    @Override
    public List<Question> fetchByTags(List<QuestionTag> questionTags){
        TypedQuery<Question> query = entityManager.createQuery("select q from Question q inner join q.tags tags where tags in :tags", Question.class);
        query.setParameter("tags",questionTags);
        return query.getResultList();
    }

    @Override
    public void delete(Question question) {

    }

    @Override
    public Question fetch(String uuid) {
        TypedQuery<Question> query = entityManager.createQuery("select q from Question q where q.uuid = :uuid", Question.class);
        query.setParameter("uuid",uuid);
        return query.getSingleResult();
    }
}

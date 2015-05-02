package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.QuestionTagDao;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class QuestionTagDaoImpl extends AbstractDaoImpl<QuestionTag> implements QuestionTagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<QuestionTag> getClassType() {
        return QuestionTag.class;
    }

    @Override
    public List<QuestionTag> fetchByNames(List<String> names) {
        TypedQuery<QuestionTag> query = entityManager.createQuery("select qt from QuestionTag qt where qt.name in :names", QuestionTag.class);
        query.setParameter("names",names);
        return query.getResultList();
    }
}

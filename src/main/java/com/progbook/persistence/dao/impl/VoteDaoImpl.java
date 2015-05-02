package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.VoteDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Vote;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

@Repository
public class VoteDaoImpl extends AbstractDaoImpl<Vote> implements VoteDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Vote> getClassType() {
        return Vote.class;
    }

    @Override
    public Integer getCountFor(Answer answer) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        ParameterExpression<Answer> pType = criteriaBuilder.parameter(Answer.class);
        Root<Vote> vote = countQuery.from(Vote.class);
        countQuery.select(criteriaBuilder.count(vote)).where(
                criteriaBuilder.equal(vote.get("answer"), pType)
        );
        try {
            Long answerCount = entityManager.createQuery(countQuery).setParameter(pType, answer).getSingleResult();
            return answerCount.intValue();
        }
        catch (NoResultException e){
            return 0;
        }

    }
}

package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.QuestionDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class QuestionDaoImpl extends AbstractDaoImpl<Question> implements QuestionDao  {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Question> getClassType() {
        return Question.class;
    }

    @Override
    public void save(Question question) {
        entityManager.persist(question);
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
    public Integer getAnswerCount(String questionUuid) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        ParameterExpression<Question> pType = criteriaBuilder.parameter(Question.class);
        Root<Answer> answer = countQuery.from(Answer.class);
        countQuery.select(criteriaBuilder.count(answer)).where(
                criteriaBuilder.equal(answer.get("question"), pType)
        );
        try {
            Long answerCount = entityManager.createQuery(countQuery).setParameter(pType, fetchByUuid(questionUuid)).getSingleResult();
            return answerCount.intValue();
        }
        catch (NoResultException e){
            return 0;
        }
    }

}

package com.progbook.persistence.dao.impl;


import com.progbook.persistence.dao.AbstractDao;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractDaoImpl<T> implements AbstractDao<T> {

    protected abstract EntityManager getEntityManager();
    protected abstract Class<T> getClassType();

    @Override
    public void save(T entity){
        getEntityManager().persist(entity);
    }

    @Override
    public T fetchById(long id){
        return getEntityManager().find(getClassType(),id);
    }

    @Override
    public T fetchByUuid(String uuid){
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getClassType());
        Root<T> entity = query.from(getClassType());
        ParameterExpression<String> pType = criteriaBuilder.parameter(String.class);
        query.select(entity)
                .where(
                        criteriaBuilder.equal(entity.get("uuid"), pType)
                );
        return getEntityManager().createQuery(query).setParameter(pType,uuid).getSingleResult();
    }

    @Override
    public List<T> fetchAll(){
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getClassType());
        Root<T> entity = query.from(getClassType());
        query.select(entity);
        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    public void delete(T entity){
        getEntityManager().remove(entity);
    }
}

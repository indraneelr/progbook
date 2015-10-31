package com.progbook.persistence.repository;

import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class GenericRepository<T , ID extends Serializable> {

    private QueryDslJpaRepository<T, ID> queryDslJpaRepository;

    public GenericRepository(JpaEntityInformation<T, ID> entityManagerInformation, EntityManager entityManager) {
        queryDslJpaRepository = new QueryDslJpaRepository<>(entityManagerInformation, entityManager);
    }

    public List<T> findAll(Predicate predicate) {
        return queryDslJpaRepository.findAll(predicate);
    }

    public List<T> findAll() {
        return queryDslJpaRepository.findAll();
    }

    public T findOne(Predicate predicate) {
        return queryDslJpaRepository.findOne(predicate);
    }

    public T save(T entity) {
        if(entity != null){
            return queryDslJpaRepository.save(entity);
        }
        return null;
    }

    public void delete(String uuid) {
//        queryDslJpaRepository.delete(fi);
    }

    public Long count(Predicate predicate){
        return queryDslJpaRepository.count(predicate);
    }
}

package com.progbook.persistence.dao.impl;

import com.mysema.query.types.Predicate;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;



public class GenericRepository<T,ID extends Serializable>{

        private QueryDslJpaRepository<T,ID> queryDslJpaRepository;

    public GenericRepository(JpaEntityInformation<T,ID> entityManagerInformation, EntityManager entityManager) {
        queryDslJpaRepository = new QueryDslJpaRepository<>(entityManagerInformation,entityManager);
    }

    public List<T> findAll(Predicate predicate){
        return queryDslJpaRepository.findAll(predicate);
    }

    public List<T> findAll(){
        return queryDslJpaRepository.findAll();
    }

    public T findOne(Predicate predicate){
        return queryDslJpaRepository.findOne(predicate);
    }

    public T save(T entity){
        return queryDslJpaRepository.save(entity);
    }

    public void delete(String uuid){
//        queryDslJpaRepository.delete(fi);
    }
}

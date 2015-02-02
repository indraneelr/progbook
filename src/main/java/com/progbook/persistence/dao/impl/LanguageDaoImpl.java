package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.LanguageDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LanguageDaoImpl implements LanguageDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Language language) {
        entityManager.persist(language);
    }

    @Override
    public Language fetch(long id) {
        return entityManager.find(Language.class,id);
    }

    @Override
    public Language fetch(String uuid) {
        return entityManager.createQuery("select l from Language l where l.uuid = :uuid",Language.class)
                .setParameter("uuid",uuid).getSingleResult();
    }

    @Override
    public List<Language> fetchAll() {
        return entityManager.createQuery("select l from Language",Language.class).getResultList();
    }

    @Override
    public List<Language> fetchByNames(List<String> names) {
        return entityManager.createQuery("select l from Language l where l.name in :names",Language.class)
                .setParameter("names",names).getResultList();
    }

    @Override
    public void delete(Answer answer) {

    }
}

package com.progbook.persistence.dao.impl;

import com.progbook.persistence.dao.LanguageDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LanguageDaoImpl extends AbstractDaoImpl<Language> implements LanguageDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    protected Class<Language> getClassType() {
        return Language.class;
    }

    @Override
    public void save(Language language) {
        entityManager.persist(language);
    }

    @Override
    public List<Language> fetchByNames(List<String> names) {
        return entityManager.createQuery("select l from Language l where l.name in :names",Language.class)
                .setParameter("names",names).getResultList();
    }

}

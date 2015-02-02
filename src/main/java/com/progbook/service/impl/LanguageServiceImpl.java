package com.progbook.service.impl;

import com.progbook.persistence.dao.LanguageDao;
import com.progbook.persistence.model.Language;
import com.progbook.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageDao languageDao;

    @Autowired
    public LanguageServiceImpl(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    @Override
    public Language fetchById(String uuid) {
        return languageDao.fetch(uuid);
    }

    @Override
    public List<Language> fetchAll() {
        return languageDao.fetchAll();
    }

    @Override
    public Set<Language> fetchByAnswer(String answerId) {
        return null;
    }

    @Override
    public void save(Language language) {
        languageDao.save(language);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Language> fetchByNames(List<String> languageNames) {
        return languageDao.fetchByNames(languageNames);
    }
}

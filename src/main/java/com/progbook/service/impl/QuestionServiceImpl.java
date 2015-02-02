package com.progbook.service.impl;

import com.progbook.persistence.dao.QuestionDao;
import com.progbook.persistence.model.Question;
import com.progbook.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;


    @Override
    public List<Question> fetchAll() {
        return questionDao.fetchAll();
    }

    @Override
    public List<Question> filterByTitle(String title) {
        return questionDao.filterByTitle(title);
    }

    @Override
    public Set<Question> fetchByCategory(String category) {
        return null;
    }

    @Override
    public Set<Question> fetchByTags(List<String> tags) {
        return null;
    }

    @Override
    public Question fetchByUuid(String uuid) {
        return questionDao.fetch(uuid);
    }

    @Override
    public void save(Question question) {
        questionDao.save(question);
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Integer getAnswerCountFor(String questionId) {
        return null;
    }
}

package com.progbook.service.impl;

import com.progbook.persistence.dao.QuestionTagDao;
import com.progbook.persistence.model.QuestionTag;
import com.progbook.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private QuestionTagDao questionTagDao;

    @Override
    public QuestionTag fetchByUuid(String uuid) {
        return questionTagDao.fetchByUuid(uuid);
    }

    @Override
    public List<QuestionTag> fetchAll() {
        return questionTagDao.fetchAll();
    }

    @Override
    public List<QuestionTag> fetchByQuestion(String questionId) {
        return null;
    }

    @Override
    public List<QuestionTag> fetchByNames(List<String> tags) {
        return questionTagDao.fetchByNames(tags);
    }

    @Override
    public void save(QuestionTag tag) {
        questionTagDao.save(tag);
    }

    @Override
    public void delete(String uuid) {
        questionTagDao.delete(questionTagDao.fetchByUuid(uuid));
    }
}

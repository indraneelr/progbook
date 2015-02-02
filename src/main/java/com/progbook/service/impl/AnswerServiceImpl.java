package com.progbook.service.impl;

import com.progbook.persistence.dao.AnswerDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.Vote;
import com.progbook.service.AnswerService;
import com.progbook.service.LanguageService;
import com.progbook.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;
    private QuestionService questionService;
    private LanguageService languageService;

    @Autowired
    public AnswerServiceImpl(AnswerDao answerDao, QuestionService questionService, LanguageService languageService) {
        this.answerDao = answerDao;
        this.questionService = questionService;
        this.languageService = languageService;
    }

    @Override
    public Set<Answer> fetchByQuestion(String questionId) {
        return null;
    }

    @Override
    public List<Answer> fetchByQuestionAndLanguages(String questionUuid, List<String> languageNames) {
        Question question = questionService.fetchByUuid(questionUuid);
        return answerDao.fetchByQuestionAndLanguages(question,languageService.fetchByNames(languageNames));
    }

    @Override
    public Answer fetchById(String answerId) {
        return null;
    }

    @Override
    public Integer getVoteCountFor(String answerId) {
        return null;
    }

    @Override
    public void save(Answer answer) {

    }

    @Override
    public void saveVote(Vote vote) {

    }

    @Override
    public void delete(String answerId) {

    }

}

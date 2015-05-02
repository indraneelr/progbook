package com.progbook.service.impl;

import com.progbook.persistence.dao.AnswerDao;
import com.progbook.persistence.dao.VoteDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.Vote;
import com.progbook.service.AnswerService;
import com.progbook.service.LanguageService;
import com.progbook.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerDao answerDao;
    private QuestionService questionService;
    private LanguageService languageService;
    private VoteDao voteDao;

    @Autowired
    public AnswerServiceImpl(AnswerDao answerDao, QuestionService questionService, LanguageService languageService, VoteDao voteDao) {
        this.answerDao = answerDao;
        this.questionService = questionService;
        this.languageService = languageService;
        this.voteDao = voteDao;
    }

    @Override
    public List<Answer> fetchByQuestion(String questionUuid) {
        Question question = questionService.fetchByUuid(questionUuid);
        return answerDao.fetchByQuestionAndLanguages(question, Collections.EMPTY_LIST);
    }

    @Override
    public List<Answer> fetchByQuestionAndLanguages(String questionUuid, List<String> languageNames) {
        Question question = questionService.fetchByUuid(questionUuid);
        return answerDao.fetchByQuestionAndLanguages(question,languageService.fetchByNames(languageNames));
    }

    @Override
    public Answer fetchByUuid(String answerUuid) {
        return answerDao.fetchByUuid(answerUuid);
    }

    @Override
    public Integer getVoteCountFor(String answerUuid) {
        return voteDao.getCountFor(answerDao.fetchByUuid(answerUuid));
    }

    @Override
    public void save(Answer answer) {
        answerDao.save(answer);
    }

    @Override
    public void saveVote(Vote vote) {
        voteDao.save(vote);
    }

    @Override
    public void delete(String answerUuid) {
        answerDao.delete(answerDao.fetchByUuid(answerUuid));
    }

}

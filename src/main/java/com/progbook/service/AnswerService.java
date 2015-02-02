package com.progbook.service;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Vote;

import java.util.List;
import java.util.Set;

public interface AnswerService {
    Set<Answer> fetchByQuestion(String questionId);
    List<Answer> fetchByQuestionAndLanguages(String questionUuid, List<String> languageNames);
    Answer fetchById(String answerId);
    Integer getVoteCountFor(String answerId);

    void save(Answer answer);
    void saveVote(Vote vote);

    void delete(String answerId);
}

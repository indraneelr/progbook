package com.progbook.persistence.dao;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Vote;

public interface VoteDao extends AbstractDao<Vote>{

    Integer getCountFor(Answer answer);
}

package com.progbook.persistence.dao.impl;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.QAnswer;
import com.progbook.persistence.model.Question;
import com.progbook.specification.QdslPredicateWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class GenericRepositoryTest {

    JpaEntityInformation<Answer,Long> jpaEntityInformation;

    @Autowired
    EntityManager entityManager;

    GenericRepository<Answer,Long> answerRepository;

    @Autowired
    DataSource dataSource;

    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void setup(){
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);
        jpaEntityInformation = new JpaMetamodelEntityInformation<>(Answer.class,entityManager.getMetamodel());
        answerRepository = new GenericRepository<>(jpaEntityInformation,entityManager);
    }

    @Test
    public void shouldFindAllAnswersForAQuestion(){
        QAnswer qAnswer = QAnswer.answer;
        List<Answer> answers = answerRepository.findAll(new QdslPredicateWrapper(qAnswer.question.uuid.eq("question-uuid-100")));
        assertEquals(3,answers.size());
    }

    @Transactional
    @Test
    public void shouldSaveAnswerForAQuestion(){
        Answer answer = new Answer();
        answer.setContent("querydsl answer!");
        answer.setLanguage(new Language(1));
        answer.setQuestion(new Question(100));

        Answer newAnswer = answerRepository.save(answer);

        assertNotNull(newAnswer);
        assertThat(newAnswer.getId(),is(not(0L)));
    }
}
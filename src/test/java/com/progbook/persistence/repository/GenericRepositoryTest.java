package com.progbook.persistence.repository;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.Application;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.*;
import com.progbook.persistence.repository.GenericRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
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
        List<Answer> answers = answerRepository.findAll(QAnswer.answer.question.uuid.eq("question-uuid-100"));
        assertEquals(3,answers.size());
        assertEquals("Its simple. RTFM.alert('you suck!');", answers.get(0).getContent());
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
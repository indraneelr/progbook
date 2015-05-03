package com.progbook.specification;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.FilterCriteria;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.dao.impl.GenericRepository;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class QdslQuestionFilterTest {

    JpaEntityInformation<Question,Long> jpaEntityInformation;

    @Autowired
    EntityManager entityManager;

    GenericRepository<Question,Long> questionRepository;

    @Autowired
    DataSource dataSource;

    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void setup(){
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);
        jpaEntityInformation = new JpaMetamodelEntityInformation<>(Question.class,entityManager.getMetamodel());
        questionRepository = new GenericRepository<>(jpaEntityInformation,entityManager);
    }

    @Test
    public void shouldRetrieveQuestionsWithGivenTags(){
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("tags","loops,setup");
        List<Question> questions = questionRepository.findAll(filterCriteria.toPredicate(Question.class));

        assertEquals(3,questions.size());
    }

    @Test
    public void shouldRetrieveQuestionsWithGivenUuid(){
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("uuid","question-uuid-100");
        Question question = questionRepository.findOne(filterCriteria.toPredicate(Question.class));

        assertEquals(100,question.getId());
    }
}
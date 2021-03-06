package com.progbook.specification;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.Application;
import com.progbook.FilterCriteria;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.repository.GenericRepository;
import com.progbook.persistence.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
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

        assertEquals(100, question.getId());
    }


    @Test
    public void shouldGetListOfAllQuestions(){
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("tags","loops,setup");
        List<Question> questions = questionRepository.findAll();

        assertThat(questions.size(),is(4));
    }

    @Test
    public void shouldGetAFilteredListOfQuestionsByTitle(){
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("title","while loop");
        List<Question> questions = questionRepository.findAll(filterCriteria.toPredicate(Question.class));

        assertThat(questions.size(),is(1));
        assertThat(questions.get(0).getTitle(),is("while loop"));
    }


}
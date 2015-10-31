package com.progbook.specification;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.FilterCriteria;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.repository.GenericRepository;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsIn;
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

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class QdslAnswerFilterTest {


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
    public void shouldGetAListOfAllAnswersForGivenQuestion(){
        dbSetupTracker.skipNextLaunch();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("question","question-uuid-100");
        List<Answer> answers = answerRepository.findAll(filterCriteria.toPredicate(Answer.class));

        assertThat(answers.size(),is(3));
        for (Answer answer : answers) {
            assertThat(answer.getId(), IsIn.isOneOf(100L,101L,106L));
        }
    }
    @Test
    public void shouldGetAListOfAllAnswersForGivenQuestionAndLanguages(){
        dbSetupTracker.skipNextLaunch();
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("question","question-uuid-100");
        filterCriteria.put("language","python");
        List<Answer> answers = answerRepository.findAll(filterCriteria.toPredicate(Answer.class));

        assertThat(answers.size(),is(1));
        for (Answer answer : answers) {
            assertThat(answer.getId(), IsIn.isOneOf(106L));
        }
    }

    @Test
    @Transactional
    public void shouldSaveAnswerToAQuestion(){
        Answer answer = new Answer();
        answer.setUuid("answer-uuid-blah");
        answer.setQuestion(new Question(100));
        Answer savedAnswer = answerRepository.save(answer);
        assertNotNull(savedAnswer);
        assertThat(answer.getId(), Matchers.greaterThan(0L));
    }

    @Test
    public void shouldSaveVotesForAGivenAnswer(){
        fail("not yet implemented");
    }

    @Test
    public void shouldSaveCommentsForAGivenAnswer(){
        fail("not yet implemented");
    }

    @Test
    public void shouldGetAListOfAllAnswersForGivenQuestionSortedByVotes(){
        dbSetupTracker.skipNextLaunch();
        fail("not yet implemented");
    }

    @Test
    public void shouldGetACountOfAllAnswersForGivenQuestionGroupedByLanguage(){
        dbSetupTracker.skipNextLaunch();
    }

    @Test
    public void shouldProvideTheAbilityToSelectVersionNumberOftheLanguageWhileAddingAnAnswer(){
        fail("not yet implemented");
    }
}

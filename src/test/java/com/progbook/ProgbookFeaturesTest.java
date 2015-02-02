package com.progbook;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import com.progbook.service.AnswerService;
import com.progbook.service.QuestionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class ProgbookFeaturesTest {
    @Autowired
    QuestionService questionService;
    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    DataSource dataSource;
    @Autowired
    private AnswerService answerService;

    @Before
    public void setup(){
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public void shouldGetListOfAllQuestions(){
        dbSetupTracker.skipNextLaunch();
        List<Question> questions = questionService.fetchAll();
        assertNotNull(questions);
        assertEquals(4,questions.size());
    }

    @Test
    public void shouldGetAFilteredListOfQuestionsByTitle(){
        dbSetupTracker.skipNextLaunch();
        List<Question> questions = questionService.filterByTitle("loop");
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }

    @Test
    public void shouldGetAListOfAllAnswersForGivenQuestionWithComments(){
        dbSetupTracker.skipNextLaunch();
        List<Answer> answers = answerService.fetchByQuestionAndLanguages("question-uuid-100", Arrays.asList(""));
        assertNotNull(answers);
        assertEquals(3,answers.size());

        assertEquals(100,answers.get(0).getId());
        assertEquals(2,answers.get(0).getComments().size());
        assertEquals("Sorry should have done that!",answers.get(0).getComments().get(0).getContent());

        assertEquals(101,answers.get(1).getId());
        assertEquals(0,answers.get(1).getComments().size());

        assertEquals(106,answers.get(2).getId());
        assertEquals(0,answers.get(2).getComments().size());
    }
    @Test
    public void shouldGetAListOfAllAnswersForGivenQuestionAndLanguages(){
        dbSetupTracker.skipNextLaunch();
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
        fail("not yet implemented");
    }

    @Test
    public void shouldSaveVotesForAGivenAnswer(){
        fail("not yet implemented");
    }

    @Test
    public void shouldSaveCommentsForAGivenAnswer(){
        fail("not yet implemented");
    }
}

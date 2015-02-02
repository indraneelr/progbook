package com.progbook.persistence;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.persistence.dao.QuestionDao;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class QuestionPersistenceTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    QuestionDao questionDao;

    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void setup(){
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource),CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public void shouldFetchQuestionsByTags(){
        dbSetupTracker.skipNextLaunch();
        List<Question> questions = questionDao.fetchByTags(Arrays.asList(new QuestionTag(2,"loops")));
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }

    @Test
    public void shouldFetchAllQuestions(){
        dbSetupTracker.skipNextLaunch();
        List<Question> questions = questionDao.fetchAll();
        assertNotNull(questions);
        assertEquals(4,questions.size());
    }
}

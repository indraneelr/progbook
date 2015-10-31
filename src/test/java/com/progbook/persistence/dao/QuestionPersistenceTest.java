package com.progbook.persistence.dao;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.Application;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.dao.QuestionDao;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
@SpringApplicationConfiguration(classes = Application.class)
@IntegrationTest
public class QuestionPersistenceTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    QuestionDao questionDao;

    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void setup(){
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public void shouldFetchQuestionsByTags(){
        dbSetupTracker.skipNextLaunch();
        List<Question> questions = questionDao.fetchByTags(Arrays.asList(new QuestionTag(2,"loops"),new QuestionTag(1,"setup")));
        assertNotNull(questions);
        assertEquals(3, questions.size());
        assertNotNull(questions.get(0).getTags());
        assertTrue(questions.get(0).getTags().size()>0);
    }

    @Test
    public void shouldFetchAllQuestions(){
        dbSetupTracker.skipNextLaunch();
        List<Question> questions = questionDao.fetchAll();
        assertNotNull(questions);
        assertEquals(4,questions.size());
    }
}

package com.progbook.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.Application;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Person;
import com.progbook.persistence.model.Question;
import com.progbook.representation.command.AnswerSaveRepresentation;
import com.progbook.representation.command.RelatedEntity;
import com.progbook.representation.query.AnswerRepresentation;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.net.URL;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

@WebIntegrationTest("server.port:9090")
public class AnswerResourceTest {

    private URL base;
    private RestTemplate template;
    @Autowired
    private DataSource dataSource;

    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:9090/");
        template = new TestRestTemplate();
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);
    }

    @Test
    public  void shouldGetAnswersByQuestionUuid(){
        dbSetupTracker.skipNextLaunch();
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("question", "question-uuid-100");

        ResponseEntity<Collection> answerResponseEntity = template.getForEntity(this.base + "/answers?question={question}", Collection.class, queryParams);
        Collection<AnswerRepresentation> answers = answerResponseEntity.getBody();
        assertThat(answers.size(), is(3));
    }

    @Test
    public void shouldGetAnswersByQuestionAndLanguage(){
        dbSetupTracker.skipNextLaunch();
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("question", "question-uuid-100");
        queryParams.put("language", "java");

        ResponseEntity<Collection> answerResponseEntity = template.getForEntity(this.base + "/answers?question={question}&language={language}", Collection.class, queryParams);
        Collection<LinkedHashMap<String,Object>> answers = answerResponseEntity.getBody();
        assertThat(answers.size(),is(1));
        assertThat(answers.iterator().next().get("id"), CoreMatchers.<Object>is(101));
    }

    @Test
    public void shouldGetAnswerByUuid(){

    }

    @Test
    public void shouldSaveAnswer() throws JsonProcessingException {
        ResponseEntity<AnswerRepresentation> answerResponseEntity = template.postForEntity(this.base+"/answers", answerToSave(), AnswerRepresentation.class);
        assertTrue(answerResponseEntity.getStatusCode().is2xxSuccessful());
        assertThat(answerResponseEntity.getBody().getId(), Matchers.greaterThan(0L));
    }
    private AnswerSaveRepresentation answerToSave() throws JsonProcessingException {
        AnswerSaveRepresentation answer = new AnswerSaveRepresentation();
        answer.setContent("This is a big answer, elaborating the question - how to save a question");
        answer.setLanguage(new RelatedEntity(1L));
        answer.setQuestion(new RelatedEntity(100L));
        answer.setCreator(new RelatedEntity(1L));
        return answer;
    }

    private Answer answerToSave2() throws JsonProcessingException {
        Answer answer = new Answer();
        answer.setContent("This is a big answer, elaborating the question - how to save a question");
        answer.setLanguage(new Language(1L));
        answer.setQuestion(new Question(100L));
        answer.setCreator(new Person(1L));
        return answer;
    }
}
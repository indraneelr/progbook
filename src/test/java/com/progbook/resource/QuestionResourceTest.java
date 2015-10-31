package com.progbook.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.progbook.Application;
import com.progbook.persistence.CommonDbOperations;
import com.progbook.persistence.model.Category;
import com.progbook.persistence.model.Person;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;
import com.progbook.representation.query.QuestionRepresentation;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)

@WebIntegrationTest("server.port:9090")
public class QuestionResourceTest {

    private URL base;
    private RestTemplate template;
    @Autowired
    private DataSource dataSource;

    static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:9090/");
        template = new TestRestTemplate();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), CommonDbOperations.LOAD_STARTER_DATASET);
        dbSetupTracker.launchIfNecessary(dbSetup);

    }

    @Test
    public void testGetAllPublished2() throws Exception {
        dbSetupTracker.skipNextLaunch();
        ResponseEntity<List> questionResponseEntity = template.getForEntity(this.base + "/questions", List.class);
        List<Question> questions = questionResponseEntity.getBody();

        assertNotNull(questions);
        assertThat(questions.size(), Is.is(4));
    }


    @Test
    public void testGetAllPublished() throws Exception {
        dbSetupTracker.skipNextLaunch();
        MvcResult mvcResult = mockMvc.perform(get("/questions").accept(MediaType.APPLICATION_JSON)).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        System.out.println(contentAsString);
        assertNotNull(contentAsString);
        assertThat(contentAsString.length()>10,Is.is(true));
    }

    @Test
    public void testGetById() throws Exception {

    }

    @Test
    public void testAddQuestion() throws Exception {
        ResponseEntity<Question> responseEntity = template.postForEntity(this.base + "/questions", questionToSave(), Question.class);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful(), Is.is(true));
        assertThat(responseEntity.getBody().getId(), Matchers.greaterThan(0L));
    }

    @Test
    public void jacksonParserTest() throws IOException {
        String valueAsString = new ObjectMapper().writeValueAsString(questionToSave());
        Question question = new ObjectMapper().readValue(valueAsString, Question.class);
        System.out.println(question);
    }
    private Question questionToSave() throws JsonProcessingException {
        Question question = new Question();
        question.setTitle("how  to save a question?");
        question.setDescription("This is a big description, elaborating the question - how to save a question");
        question.setCategory(new Category(1L, null, null));
        question.setTags(questionTags());
        question.setCreator(questionCreator());
        return question;
    }

    private QuestionRepresentation questionToSave2() throws JsonProcessingException {
        QuestionRepresentation question = new QuestionRepresentation();
        question.setTitle("how  to save a question?");
        question.setDescription("This is a big description, elaborating the question - how to save a question");
//        question.setCategory(new Category(1L, null, null));
//        question.setTags(questionTags());
//        question.setCreator(questionCreator());
        return question;
    }

    private Person questionCreator(){
        Person person = new Person();
        person.setId(1L);
        return person;
    }

    private List<QuestionTag> questionTags() {
        ArrayList<QuestionTag> tags = new ArrayList<>();
        tags.add(new QuestionTag(1L,"test"));
        return tags;
    }

}
import com.progbook.persistence.dao.AnswerDao;
import com.progbook.persistence.dao.QuestionDao;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Category;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Question;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class TestPersistence {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Test
    @Transactional
    public void shouldSaveAndRetrieveQuestion() {
        Question question = createQuestion("awesome question", "whats the question?", "java");
        questionDao.save(question);
        List<Question> questions = questionDao.fetchAll();
        assertEquals(1,questions.size());
        Question firstQuestion = questions.get(0);
        Answer answer = createAnswer("its about whether this works!!",firstQuestion,null);
        answerDao.save(answer);
        entityManager.refresh(firstQuestion);
        assertEquals("awesome question", firstQuestion.getTitle());
        assertEquals(1, firstQuestion.getAnswers().size());
        assertEquals("its about whether this works!!", firstQuestion.getAnswers().iterator().next().getContent());
    }

    @Test
    @Transactional
    public void shouldSaveAndRetriveAnswers(){
        Question question = createQuestion("awesome question", "whats the question?", "java");
        questionDao.save(question);
        List<Question> questions = questionDao.fetchAll();

        Answer answer = createAnswer("its about whether this works!!",questions.get(0),null);
        answerDao.save(answer);
        List<Answer> answers = answerDao.fetchAll();
        assertEquals(1,answers.size());
        assertNotNull(answers.get(0).getQuestion());
        assertEquals("awesome question", answers.get(0).getQuestion().getTitle());
    }

    private Question createQuestion(String title, String content, String category) {
        Question question = new Question();
        Category categ = new Category();
        categ.setName(category);
        question.setCategory(categ);
        question.setContent(content);
        question.setTitle(title);
        return question;
    }

    private Answer createAnswer(String content, Question question, Language language){
        Answer answer = new Answer();
        answer.setContent(content);
//        Language lang = new Language();
//        lang.setName(language);
        answer.setLanguage(language);
        answer.setQuestion(question);
        return answer;
    }

}

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
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:TestApplicationContext.xml" })
public class QuestionPersistenceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void shouldFetchQuestionsByTags(){

    }

}

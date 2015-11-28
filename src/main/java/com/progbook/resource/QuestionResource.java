package com.progbook.resource;

import com.progbook.FilterCriteria;
import com.progbook.persistence.model.Category;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.repository.GenericRepository;
import com.progbook.representation.command.QuestionSaveRepresentation;
import com.progbook.representation.query.QuestionRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionResource {
    @Autowired
    GenericRepository<Question, Long> questionRepository;
    @Autowired
    GenericRepository<Category, Long> categoryRepository;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<QuestionRepresentation> getAllPublished() {
        List<Question> questions = questionRepository.findAll();
        Collection<QuestionRepresentation> response = QuestionRepresentation.map(questions);
        return response;
    }

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    QuestionSaveRepresentation getById(@PathVariable(value = "id") String questionId) {

        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("id", questionId);
        return new QuestionSaveRepresentation(questionRepository.findOne(filterCriteria.toPredicate(Question.class)));
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public
    @ResponseBody
    ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionRepository.save(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @Transactional
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<Collection<Category>> getCategories() {
        Collection<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }
}

package com.progbook.resource;

import com.progbook.FilterCriteria;
import com.progbook.persistence.model.Answer;
import com.progbook.persistence.repository.GenericRepository;
import com.progbook.representation.command.AnswerSaveRepresentation;
import com.progbook.representation.query.AnswerRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerResource {

    @Autowired
    GenericRepository<Answer,Long> answerRepository;

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    Collection<AnswerRepresentation> getAnswersByQuestion(@RequestParam(value = "question") String questionUuid, @RequestParam(value = "language",required = false) String language){
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("question",questionUuid);
        if(language != null){
            filterCriteria.put("language", language);
        }
        return AnswerRepresentation.map(answerRepository.findAll(filterCriteria.toPredicate(Answer.class)));
    }

    @Transactional
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public @ResponseBody List<Answer> getAnswersById(@PathVariable(value = "id") String answerId){
        FilterCriteria filterCriteria = new FilterCriteria();
        filterCriteria.put("uuid",answerId);
        return answerRepository.findAll(filterCriteria.toPredicate(Answer.class));
    }


    @Transactional
    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveAnswer( @RequestBody AnswerSaveRepresentation answer){
        Answer savedAnswer = answerRepository.save(answer.toAnswer());
        return ResponseEntity.ok(new AnswerRepresentation(savedAnswer));
    }


}

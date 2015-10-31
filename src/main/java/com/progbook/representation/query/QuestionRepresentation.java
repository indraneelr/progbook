package com.progbook.representation.query;

import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class QuestionRepresentation {
    private Long id;
    private String uuid;
    private String title;
    private String category;
    private Date created;
    private Collection<AnswerRepresentation> answers;
    private PersonRepresentation creator;
    private Collection<String> tags;
    private String description;

    public QuestionRepresentation() {
    }

    public QuestionRepresentation(Question question){
        if (question == null) return;
        id = question.getId();
        uuid = question.getUuid();
        title = question.getTitle();
        category = question.getCategory().getName();
        created = question.getDateCreated();
        description = question.getDescription();
        answers = AnswerRepresentation.map(question.getAnswers());
        creator = new PersonRepresentation(question.getCreator());
        tags = new ArrayList<>();
        for (QuestionTag questionTag : question.getTags()) {
            tags.add(questionTag.getName());
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Collection<AnswerRepresentation> getAnswers() {
        return answers;
    }

    public void setAnswers(Collection<AnswerRepresentation> answers) {
        this.answers = answers;
    }

    public PersonRepresentation getCreator() {
        return creator;
    }

    public void setCreator(PersonRepresentation creator) {
        this.creator = creator;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public static Collection<QuestionRepresentation> map(Collection<Question> questions) {
        Collection<QuestionRepresentation> questionRepresentations = new ArrayList<>();
        if(questions != null){
            for (Question question : questions) {
                questionRepresentations.add(new QuestionRepresentation(question));
            }
        }
        return  questionRepresentations;
    }

    /*public Question toQuestion(){
        Question question = new Question();
        question.setId(this.getId());
        question.setUuid(this.getUuid());
        question.setTitle(this.getTitle());
        question.setDescription(this.getDescription());
        question.setAnswers(AnswerRepresentation.map(this.getAnswers()));
        question.setCategory(new Category(0, this.getCategory(), null));
        question.setCreator(this.getCreator().toPerson());
        question.setDateCreated(this.getCreated());
        question.setTags(toTags(this.getTags()));
        return question;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

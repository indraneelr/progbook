package com.progbook.representation.command;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Language;
import com.progbook.persistence.model.Person;
import com.progbook.persistence.model.Question;

import java.util.Date;

public class AnswerSaveRepresentation {
    private long id;
    private RelatedEntity language;
    private Date dateCreated;
    private RelatedEntity question;
    private RelatedEntity creator;
    private String uuid;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RelatedEntity getLanguage() {
        return language;
    }

    public void setLanguage(RelatedEntity language) {
        this.language = language;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public RelatedEntity getQuestion() {
        return question;
    }

    public void setQuestion(RelatedEntity question) {
        this.question = question;
    }

    public RelatedEntity getCreator() {
        return creator;
    }

    public void setCreator(RelatedEntity creator) {
        this.creator = creator;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Answer toAnswer(){
        Answer answer = new Answer();
        answer.setId(this.id);
        answer.setContent(this.content);
        answer.setQuestion(new Question(question.getId()));
        answer.setLanguage(new Language(language.getId()));
        answer.setUuid(this.uuid);
        answer.setCreator(new Person(creator.getId()));
        answer.setDateCreated(dateCreated );
        return answer;
    }
}

package com.progbook.representation.query;

import com.progbook.persistence.model.Answer;

import java.util.*;

public class AnswerRepresentation {
    private Long id;
    private String uuid;
    private PersonRepresentation creator;
    private Date created;
    private LanguageRepresention language;
    private Collection<CommentRepresentation> comments;
    private String content;

    public AnswerRepresentation() {
    }


    public AnswerRepresentation(Answer answer) {
        if(answer == null ) return;
        id = answer.getId();
        uuid = answer.getUuid();
        content = answer.getContent();
        creator = new PersonRepresentation(answer.getCreator());
        created = answer.getDateCreated();
        language = new LanguageRepresention(answer.getLanguage());
        comments = CommentRepresentation.map(answer.getComments());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public PersonRepresentation getCreator() {
        return creator;
    }

    public void setCreator(PersonRepresentation creator) {
        this.creator = creator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public LanguageRepresention getLanguage() {
        return language;
    }

    public void setLanguage(LanguageRepresention language) {
        this.language = language;
    }

    public static Collection<AnswerRepresentation> map(Collection<Answer> answers) {
        Collection<AnswerRepresentation> answerRepresentations = new ArrayList<>();
        if (answers != null) {
            for (Answer answer : answers) {
                answerRepresentations.add(new AnswerRepresentation(answer));
            }
        }
        return answerRepresentations;
    }

    public Collection<CommentRepresentation> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentRepresentation> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

   /* public static Collection<Answer> map(Collection<AnswerRepresentation> answers) {
        List<Answer> answerList = new ArrayList<>();
        if(answers != null){
            for (AnswerRepresentation answerRepresentation : answers) {
                answerList.add(answerRepresentation.toAnswer());
            }
        }
        return answerList;
    }*/

}

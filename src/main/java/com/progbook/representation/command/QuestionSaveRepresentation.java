package com.progbook.representation.command;

import com.progbook.persistence.model.Answer;
import com.progbook.persistence.model.Question;
import com.progbook.persistence.model.QuestionTag;

import java.util.*;

public class QuestionSaveRepresentation {
    private long id;

    private String uuid;

    private String title;

    private RelatedEntity category;

    private Date dateCreated;

    private List<RelatedEntity> tags;

    private Set<RelatedEntity> answers;

    private RelatedEntity creator;

    private String description;

    public QuestionSaveRepresentation(Question question) {
        if(question != null){
            id = question.getId();
            uuid = question.getUuid();
            title = question.getTitle();
            category = new RelatedEntity(question.getCategory().getId());
            dateCreated = question.getDateCreated();
            if(question.getTags() != null){
                tags = new ArrayList<>();
                for (QuestionTag questionTag : question.getTags()) {
                    tags.add(new RelatedEntity(questionTag.getId()));
                }
            }
            if(question.getAnswers() != null){
                answers = new LinkedHashSet<>();
                for (Answer answer : question.getAnswers()) {
                    answers.add(new RelatedEntity(answer.getId()));
                }
            }
            creator = new RelatedEntity(question.getCreator().getId());
            description = question.getDescription();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public RelatedEntity getCategory() {
        return category;
    }

    public void setCategory(RelatedEntity category) {
        this.category = category;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<RelatedEntity> getTags() {
        return tags;
    }

    public void setTags(List<RelatedEntity> tags) {
        this.tags = tags;
    }

    public Set<RelatedEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<RelatedEntity> answers) {
        this.answers = answers;
    }

    public RelatedEntity getCreator() {
        return creator;
    }

    public void setCreator(RelatedEntity creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

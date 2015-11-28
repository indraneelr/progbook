package com.progbook.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class ContentBlock {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String uuid;

    @Column
    private String content;

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    @Column
    private String title;

    @Column
    private String type;

    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToMany
    private List<QuestionTag> tags;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @ManyToOne
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person creator;

    public ContentBlock() {
    }

    public ContentBlock(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QuestionTag> getTags() {
        return tags;
    }

    public void setTags(List<QuestionTag> tags) {
        this.tags = tags;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }
}

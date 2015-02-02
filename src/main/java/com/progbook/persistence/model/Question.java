package com.progbook.persistence.model;

import javax.persistence.*;
import javax.persistence.metamodel.ListAttribute;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="question_tags_map")
    private Set<QuestionTag> tags;

    // if you remove cascadeType.all answers will not be saved.
    @OneToMany(mappedBy = "question",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Answer> answers;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Set<QuestionTag> getTags() {
        return tags;
    }

    public void setTags(Set<QuestionTag> tags) {
        this.tags = tags;
    }
}

package com.progbook.persistence.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.persistence.metamodel.ListAttribute;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String uuid;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "date_created")
    private Date dateCreated;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="question_tags_map",joinColumns = {@JoinColumn(name = "question_id")},inverseJoinColumns = {@JoinColumn(name = "question_tag_id")})
    private List<QuestionTag> tags;

    // if you remove cascadeType.all answers will not be saved.
    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person creator;

    @Column(name="description")
    private String description;

    public Question() {
    }

    public Question(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<QuestionTag> getTags() {
        return tags;
    }

    public void setTags(List<QuestionTag> tags) {
        this.tags = tags;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;

        Question question = (Question) o;

        if (this.getUuid() != null ? !this.getUuid().equals(question.getUuid()) : question.getUuid() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return this.getUuid() != null ? this.getUuid().hashCode() : 0;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @PrePersist
    public void initializeDefaultValues(){
        if(getUuid() == null){
            setUuid(UUID.randomUUID().toString());
        }
        if(dateCreated == null){
            dateCreated = new Date();
        }
    }

}

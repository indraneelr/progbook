package com.progbook.representation.query;

import com.progbook.persistence.model.Comment;

import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

public class CommentRepresentation  implements Comparable<CommentRepresentation>{
    private Long id;
    private String uuid;
    private PersonRepresentation creator;
    private Date created;
    private String content;

    public CommentRepresentation() {
    }
    public CommentRepresentation(Comment comment) {
        if(comment == null) return;
        id = comment.getId();
        uuid = comment.getUuid();
        creator = new PersonRepresentation(comment.getCreator());
        created = comment.getDateCreated();
        content = comment.getContent();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentRepresentation that = (CommentRepresentation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        return !(content != null ? !content.equals(that.content) : that.content != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Collection<CommentRepresentation> map(Collection<Comment> comments) {
        Collection<CommentRepresentation> commentRepresentations = new TreeSet<>();
        if(comments != null){
            for (Comment comment : comments) {
                commentRepresentations.add(new CommentRepresentation(comment));
            }
        }
        return commentRepresentations;
    }

    @Override
    public int compareTo(CommentRepresentation other) {
        if (created == null && other.getCreated() == null) return  0;
        if (created == null ) return 1;
        if (other.getCreated() == null) return  -1;
        return created.compareTo(other.getCreated());
    }
}

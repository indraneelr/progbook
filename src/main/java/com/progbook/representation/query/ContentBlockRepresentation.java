package com.progbook.representation.query;

import com.progbook.persistence.model.ContentBlock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class ContentBlockRepresentation implements Comparable<ContentBlockRepresentation>{

    private Long id;
    private String uuid;
    private String title;
    private Integer sequenceNumber;
    private String content;
    private String type;

    public ContentBlockRepresentation() {
    }

    public ContentBlockRepresentation(ContentBlock contentBlock) {
        if (contentBlock == null) return;
        id = contentBlock.getId();
        uuid = getUuid();
        title = contentBlock.getTitle();
        sequenceNumber = contentBlock.getSequenceNumber();
        content = contentBlock.getContent();
        type = contentBlock.getType();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static Collection<ContentBlockRepresentation> map(List<ContentBlock> contentBlocks) {
        Collection<ContentBlockRepresentation> contentBlockRepresentations = new TreeSet<>() ;
        if(contentBlocks != null){
            for (ContentBlock contentBlock : contentBlocks) {
                contentBlockRepresentations.add(new ContentBlockRepresentation(contentBlock));
            }
        }
        return contentBlockRepresentations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentBlockRepresentation that = (ContentBlockRepresentation) o;

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


    @Override
    public int compareTo(ContentBlockRepresentation other) {
        if(sequenceNumber == null  && other.getSequenceNumber() == null) return 0;
        if(sequenceNumber == null ) return 1;
        if(other.getSequenceNumber() == null ) return -1;
        return sequenceNumber.compareTo(other.getSequenceNumber());
    }

    public static Collection<ContentBlock> map(Collection<ContentBlockRepresentation> contentBlocks) {
        List<ContentBlock> contentBlockList = new ArrayList<>();
        if(contentBlocks != null) {
            for (ContentBlockRepresentation contentBlockRepresentation : contentBlocks) {
                contentBlockList.add(contentBlockRepresentation.toContentBlock());
            }
        }
        return contentBlockList;
    }

    private ContentBlock toContentBlock() {
        ContentBlock contentBlock = new ContentBlock();
        contentBlock.setId(this.getId());
        contentBlock.setContent(this.getContent());
        contentBlock.setTitle(this.getTitle());
        contentBlock.setUuid(this.getUuid());
        contentBlock.setSequenceNumber(this.getSequenceNumber());
        contentBlock.setType(this.getType());
        return contentBlock;
    }
}

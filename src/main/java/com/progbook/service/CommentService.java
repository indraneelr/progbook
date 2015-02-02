package com.progbook.service;

import com.progbook.persistence.model.Comment;

import java.util.Set;

public interface CommentService {
    Set<Comment> fetchByAnswer(String answerId);
    Comment fetchbyId(String id);

    void save(Comment comment);
    void delete(String commentId);
}

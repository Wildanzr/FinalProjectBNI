package com.wildannn.comment.service;

import com.wildannn.comment.model.Comment;

import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment);
    List<Comment> findAll();
    Comment findById(Integer id);
    Comment update(Integer id, Comment updated);
    void delete(Integer id);
}

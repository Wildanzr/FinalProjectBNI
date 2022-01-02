package com.wildannn.post.service;

import com.wildannn.post.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment);
    List<Comment> findAll();
    Comment findById(Long id);
    Comment update(Long id, Comment comment);
    void delete(Long id);
}

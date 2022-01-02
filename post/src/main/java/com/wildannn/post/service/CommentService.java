package com.wildannn.post.service;

import com.wildannn.post.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment, Integer postId, Integer userId);
    List<Comment> findAll();
    Comment findById(Long id);
    Comment update(Long id, Comment comment);
    void delete(Integer postId, Integer userId, Long id);
}

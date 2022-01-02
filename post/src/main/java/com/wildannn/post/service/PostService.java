package com.wildannn.post.service;

import com.wildannn.post.entity.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    List<Post> findAll();
    Post findById(Long id);
    Post update(Long id, Post post);
    void delete(Long id);
}

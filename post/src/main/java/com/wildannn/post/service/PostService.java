package com.wildannn.post.service;

import com.wildannn.post.entity.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post);
    List<Post> findAll();
    Post findById(Integer id);
    Post update(Integer id, Post post);
    void delete(Integer id);
}

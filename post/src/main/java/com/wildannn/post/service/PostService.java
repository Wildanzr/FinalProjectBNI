package com.wildannn.post.service;

import com.wildannn.post.entity.Post;
import com.wildannn.post.model.PostModel;
import com.wildannn.post.model.StatModel;

import java.util.List;

public interface PostService {

    //CRUD
    Post addPost(Post post);
    List<Post> findAll();
    Post findById(Long id);
    Post update(Long id, Post post);
    void delete(Long id);

    //Logic
    PostModel convertToModel(Post post);
    List<PostModel> convertToModels(List<Post> posts);
}

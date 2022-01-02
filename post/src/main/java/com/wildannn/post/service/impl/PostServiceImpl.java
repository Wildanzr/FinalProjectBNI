package com.wildannn.post.service.impl;

import com.wildannn.post.entity.Post;
import com.wildannn.post.repository.PostRepository;
import com.wildannn.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException("Not found!");
        });
    }

    @Override
    public Post update(Integer id, Post post) {
        Post updated = this.findById(id);
        updated.setUpdated_at(post.getUpdated_at());
        updated.setUser_id(post.getUser_id());
        updated.setTraining_topic_id(post.getTraining_topic_id());
        updated.setContent(post.getContent());
        return postRepository.save(updated);
    }

    @Override
    public void delete(Integer id) {
        Post deleted = this.findById(id);
        postRepository.delete(deleted);
    }
}

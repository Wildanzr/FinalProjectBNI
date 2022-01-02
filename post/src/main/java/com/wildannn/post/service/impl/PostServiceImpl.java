package com.wildannn.post.service.impl;

import com.wildannn.post.entity.Post;
import com.wildannn.post.handler.MessageResponse;
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
    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException(MessageResponse.POST_NOT_FOUND);
        });
    }

    @Override
    public Post update(Long id, Post post) {
        Post updated = this.findById(id);
        updated.setUpdatedAt(post.getUpdatedAt());
        updated.setContent(post.getContent());
        return postRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        Post deleted = this.findById(id);
        postRepository.delete(deleted);
    }
}

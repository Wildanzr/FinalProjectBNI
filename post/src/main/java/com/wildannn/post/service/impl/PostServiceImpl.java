package com.wildannn.post.service.impl;

import com.wildannn.post.entity.Post;
import com.wildannn.post.entity.PostStat;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.repository.PostRepository;
import com.wildannn.post.repository.PostStatRepository;
import com.wildannn.post.service.PostService;
import com.wildannn.post.service.PostStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    private PostStatService statService;

    @Override
    public Post addPost(Post post) {
        Post post1 = postRepository.save(post);
        statService.make(post1);

        return post1;
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

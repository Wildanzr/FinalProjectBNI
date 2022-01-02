package com.wildannn.post.service.impl;

import com.wildannn.post.entity.Post;
import com.wildannn.post.entity.PostStat;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.model.PostModel;
import com.wildannn.post.model.StatModel;
import com.wildannn.post.repository.PostRepository;
import com.wildannn.post.service.PostService;
import com.wildannn.post.service.PostStatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostStatService statService;

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

    @Override
    public PostModel convertToModel(Post post) {
        List<StatModel> models = new ArrayList<>();
        PostStat stat = statService.findById(post.getId());
        StatModel model = statService.convertToModel(stat);
        models.add(model);

        return PostModel.builder()
                .id(post.getId())
                .content(post.getContent())
                .userId(post.getUserId())
                .trainingTopicId(post.getTrainingTopicId())
                .stats(models)
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    @Override
    public List<PostModel> convertToModels(List<Post> posts) {
        List<PostModel> models = new ArrayList<>();

        for(Post a : posts) {
            models.add(this.convertToModel(a));
        }

        return models;
    }
}

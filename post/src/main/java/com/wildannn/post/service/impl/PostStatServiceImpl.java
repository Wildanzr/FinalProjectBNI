package com.wildannn.post.service.impl;

import com.wildannn.post.entity.PostStat;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.model.StatModel;
import com.wildannn.post.repository.PostStatRepository;
import com.wildannn.post.service.PostStatService;
import com.wildannn.post.service.UserLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class PostStatServiceImpl implements PostStatService {

    @Autowired
    private final PostStatRepository statRepository;
    private final UserLikeService likeService;

    @Override
    public PostStat make() {
        log.info("EXECUTE");
        PostStat stat =  PostStat.builder()
                .comments(0)
                .likes(0)
                .shares(0)
                .build();

        return statRepository.save(stat);
    }

    @Override
    public PostStat findById(Long id) {
        return statRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException(MessageResponse.POST_STAT_NOT_FOUND);
        });
    }

    @Override
    public void delete(Long id) {
        PostStat deleted = this.findById(id);

        statRepository.delete(deleted);
        likeService.deleteAll(id);
    }

    @Override
    public void likePost(Long postId, Long userId) {
        PostStat liked = this.findById(postId);
        liked.setLikes(liked.getLikes()+1);

        likeService.like(postId, userId);
    }

    @Override
    public void unlikePost(Long postId, Long userId) {
        boolean checkLike = true;
        PostStat unlike = this.findById(postId);
        unlike.setLikes(unlike.getLikes()-1);

        if(unlike.getLikes() < 0)
            checkLike = false;

        if(checkLike)
            likeService.unlike(postId, userId);
        else
            unlike.setLikes(0);
    }

    @Override
    public StatModel convertToModel(PostStat stat) {
        return StatModel.builder()
                .likes(stat.getLikes())
                .shares(stat.getShares())
                .comments(stat.getComments())
                .createdAt(stat.getCreatedAt())
                .updatedAt(stat.getUpdatedAt())
                .build();
    }

    @Override
    public List<StatModel> convertToModels(List<PostStat> stats) {
        List<StatModel> models = new ArrayList<>();

        for(PostStat a : stats) {
            models.add(this.convertToModel(a));
        }

        return models;
    }

    @Override
    public void comment(Long postId, Long userId) {
        PostStat comment = this.findById(postId);

        comment.setComments(comment.getComments()+1);
    }

    @Override
    public void uncomment(Long postId, Long userId) {
        PostStat uncomment = this.findById(postId);
        log.info(uncomment);
        uncomment.setComments(uncomment.getComments()-1);
        log.info("AKHIR");
        log.info(uncomment);
        if(uncomment.getComments() < 0)
            uncomment.setComments(0);
    }
}

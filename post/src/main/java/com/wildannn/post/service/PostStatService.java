package com.wildannn.post.service;

import com.wildannn.post.entity.PostStat;
import com.wildannn.post.model.StatModel;

import java.util.List;

public interface PostStatService {
    PostStat make();
    PostStat findById(Long id);
    void delete(Long id);

    void likePost(Long postId, Long userId);
    void unlikePost(Long postId, Long userId);

    StatModel convertToModel(PostStat stat);
    List<StatModel> convertToModels(List<PostStat> stats);
}

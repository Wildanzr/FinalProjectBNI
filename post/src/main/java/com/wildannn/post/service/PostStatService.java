package com.wildannn.post.service;

import com.wildannn.post.entity.Post;
import com.wildannn.post.entity.PostStat;

public interface PostStatService {
    PostStat make(Post post);
    PostStat findById(Long id);
    void delete(Long id);

    void likePost(Long postId, Long userId);
    void unlikePost(Long postId, Long userId);
}

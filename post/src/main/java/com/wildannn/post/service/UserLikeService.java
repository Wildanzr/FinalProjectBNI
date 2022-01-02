package com.wildannn.post.service;

import com.wildannn.post.entity.UserLike;

public interface UserLikeService {
    UserLike like(Long postStatId, Long userId);
    void unlike(Long postStatId, Long userId);
    void deleteAll(Long postStatId);
}

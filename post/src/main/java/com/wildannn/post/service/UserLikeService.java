package com.wildannn.post.service;

public interface UserLikeService {
    void like(Long postStatId, Long userId);
    void unlike(Long postStatId, Long userId);
    void deleteAll(Long postStatId);
}

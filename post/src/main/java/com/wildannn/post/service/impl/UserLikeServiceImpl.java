package com.wildannn.post.service.impl;

import com.wildannn.post.entity.UserLike;
import com.wildannn.post.handler.MessageResponse;
import com.wildannn.post.repository.UserLikeRepository;
import com.wildannn.post.service.UserLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private final UserLikeRepository likeRepository;

    @Override
    public void like(Long postStatId, Long userId) {
        if(likeRepository.findByPostIdAndUserId(postStatId, userId) != null)
            throw new RuntimeException(MessageResponse.HAVE_LIKED);

        UserLike like = UserLike.builder()
                .postId(postStatId)
                .userId(userId)
                .build();

        likeRepository.save(like);
    }

    @Override
    public void unlike(Long postStatId, Long userId) {
        UserLike unlike = likeRepository.findByPostIdAndUserId(postStatId, userId);

        likeRepository.delete(unlike);
    }

    @Override
    public void deleteAll(Long postStatId) {
        List<UserLike> deleteAll = likeRepository.findAllByPostId(postStatId);

        likeRepository.deleteAll(deleteAll);
    }
}

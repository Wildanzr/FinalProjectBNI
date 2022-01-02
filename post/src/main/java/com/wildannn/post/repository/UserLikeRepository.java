package com.wildannn.post.repository;

import com.wildannn.post.entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike, Long> {
    UserLike findByPostIdAndUserId(Long postId, Long userId);
    List<UserLike> findAllByPostId(Long postId);
}

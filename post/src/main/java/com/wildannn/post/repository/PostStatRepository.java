package com.wildannn.post.repository;

import com.wildannn.post.entity.PostStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatRepository extends JpaRepository<PostStat, Long> {
}

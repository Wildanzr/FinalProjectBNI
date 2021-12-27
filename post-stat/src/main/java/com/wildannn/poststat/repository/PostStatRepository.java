package com.wildannn.poststat.repository;

import com.wildannn.poststat.model.PostStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostStatRepository extends JpaRepository<PostStat, Integer> {
}

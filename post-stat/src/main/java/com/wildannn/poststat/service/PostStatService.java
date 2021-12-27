package com.wildannn.poststat.service;

import com.wildannn.poststat.model.PostStat;

import java.util.List;

public interface PostStatService {
    PostStat addPostStat(PostStat postStat);
    List<PostStat> findAll();
    PostStat findById(Integer id);
    PostStat update(Integer id, PostStat postStat);
    void delete(Integer id);
}

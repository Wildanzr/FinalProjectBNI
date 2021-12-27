package com.wildannn.poststat.service.impl;

import com.wildannn.poststat.model.PostStat;
import com.wildannn.poststat.repository.PostStatRepository;
import com.wildannn.poststat.service.PostStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostStatServiceImpl implements PostStatService {

    @Autowired
    private PostStatRepository postStatRepository;

    @Override
    public PostStat addPostStat(PostStat postStat) {
        return postStatRepository.save(postStat);
    }

    @Override
    public List<PostStat> findAll() {
        return postStatRepository.findAll();
    }

    @Override
    public PostStat findById(Integer id) {
        return postStatRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException("Not Found!");
        });
    }

    @Override
    public PostStat update(Integer id, PostStat postStat) {
        PostStat updated = this.findById(id);
        updated.setUpdated_at(postStat.getUpdated_at());
        updated.setComments(postStat.getComments());
        updated.setLikes(postStat.getLikes());
        updated.setShares(postStat.getShares());

        List<Integer> temp = addNewLikes(updated.getUser_likes(), postStat.getUser_last_like_id());
        updated.setUser_likes(temp);

        return postStatRepository.save(updated);
    }

    @Override
    public void delete(Integer id) {
        PostStat deleted = this.findById(id);
        postStatRepository.delete(deleted);
    }

    public List<Integer> addNewLikes(List<Integer> before, Integer user_likes_id) {
        List<Integer> result = before;
        result.add(user_likes_id);

        return result;
    }
}
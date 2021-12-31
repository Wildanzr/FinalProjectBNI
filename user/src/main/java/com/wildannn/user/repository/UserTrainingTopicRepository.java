package com.wildannn.user.repository;

import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.model.TrainingTopicModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTrainingTopicRepository extends MongoRepository<UserTrainingTopic, String> {
    List<UserTrainingTopic> findAllByUserId(Integer userId);
    Optional<UserTrainingTopic> findByUserIdAndTrainingTopicId(Integer userId, Integer topicId);
    List<TrainingTopicModel> findAllByUserId(String userId);
    void deleteByUserIdAndTrainingTopicId(Integer userId, Integer topicId);
}

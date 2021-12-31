package com.wildannn.user.repository;

import com.wildannn.user.entity.TrainingTopic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingTopicRepository extends MongoRepository<TrainingTopic, String> {
}

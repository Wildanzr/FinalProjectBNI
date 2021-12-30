package com.wildannn.user.service;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.model.Password;

import java.util.List;

public interface UserTrainingTopicService {
    UserTrainingTopic enrollTopic(Integer userId, String topicId, Password password);
    List<UserTrainingTopic> enrolledTopics(Integer userId);
    void unEnrollTopic(Integer userId, TrainingTopic topic);
}

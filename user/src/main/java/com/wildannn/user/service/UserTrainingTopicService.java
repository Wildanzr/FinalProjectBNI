package com.wildannn.user.service;

import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.model.EnrollModel;
import com.wildannn.user.model.Password;
import com.wildannn.user.model.TrainingTopicModel;

import java.util.List;

public interface UserTrainingTopicService {
    UserTrainingTopic enrollTopic(Integer userId, Integer topicId, Password password);
    List<UserTrainingTopic> enrolledTopics(Integer userId);
    void unEnrollTopic(Integer userId, Integer topicId);
    TrainingTopicModel convertToTrainingTopicModel(UserTrainingTopic topic);
    List<TrainingTopicModel> convertToTrainingTopicModels(List<UserTrainingTopic> topics);

    EnrollModel convertToEnrollModel(UserTrainingTopic topic, Integer userId);
}

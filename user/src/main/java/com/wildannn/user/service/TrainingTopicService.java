package com.wildannn.user.service;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.model.TrainingTopicModel;

import java.util.List;

public interface TrainingTopicService {
    TrainingTopic create(TrainingTopic topic);
    TrainingTopic findById(String id);
    List<TrainingTopic> findAll();
    TrainingTopic update(String id, TrainingTopic update);
    void delete(String id);

    TrainingTopic makeTopic(TrainingTopic topic);
    TrainingTopicModel convertToModel(TrainingTopic topic);
    List<TrainingTopicModel> convertToModels(List<TrainingTopic> topics);
}

package com.wildannn.user.service.impl;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.handler.ErrorMessage;
import com.wildannn.user.model.EnrollModel;
import com.wildannn.user.model.Password;
import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.repository.UserTrainingTopicRepository;
import com.wildannn.user.service.TrainingTopicService;
import com.wildannn.user.service.UserTrainingTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTrainingTopicServiceImpl implements UserTrainingTopicService {

    @Autowired
    private final UserTrainingTopicRepository topicsRepository;
    private final TrainingTopicService topicService;


    @Override
    public UserTrainingTopic enrollTopic(Integer userId, String topicId, Password password) {
        if(topicsRepository.findByUserIdAndTrainingTopicId(userId, topicId).isPresent())
            throw new RuntimeException(ErrorMessage.HAVE_ENROLLED);

        TrainingTopic topic = topicService.findById(topicId);

        if(password.getPassword().equals(topic.getEnrollCode())) {
            UserTrainingTopic enroll = UserTrainingTopic.builder()
                    .userId(userId)
                    .topic(topic)
                    .build();

            return topicsRepository.save(enroll);
        }
        else
            throw new RuntimeException(ErrorMessage.WRONG_ENROLL_CODE);
    }

    @Override
    public List<UserTrainingTopic> enrolledTopics(Integer userId) {

        return topicsRepository.findAllByUserId(userId);
    }

    @Override
    public void unEnrollTopic(Integer userId, TrainingTopic topic) {
        UserTrainingTopic unEnroll = topicsRepository.findByUserIdAndTrainingTopicId(userId,
                Integer.valueOf(topic.getId()));

        topicsRepository.delete(unEnroll);
    }

    @Override
    public List<TrainingTopicModel> convertToTrainingTopicModels(List<UserTrainingTopic> topics) {
        List<TrainingTopicModel> models = new ArrayList<>();

        for(UserTrainingTopic a : topics) {
            models.add(convertToTrainingTopicModel(a));
        }

        return models;
    }

    @Override
    public TrainingTopicModel convertToTrainingTopicModel(UserTrainingTopic topic) {
        return TrainingTopicModel.builder()
                .id(String.valueOf(topic.getTrainingTopicId()))
                .name(topic.getTrainingTopicName())
                .build();
    }

    @Override
    public EnrollModel convertToEnrollModel(UserTrainingTopic topic, Integer userId) {
        return EnrollModel.builder()
                .userId(userId)
                .trainingTopicId(topic.getTrainingTopicId())
                .trainingTopicName(topic.getTrainingTopicName())
                .build();
    }
}

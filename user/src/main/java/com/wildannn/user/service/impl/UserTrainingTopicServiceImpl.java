package com.wildannn.user.service.impl;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.handler.ErrorMessage;
import com.wildannn.user.model.EnrollModel;
import com.wildannn.user.model.Password;
import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.repository.UserRepository;
import com.wildannn.user.repository.UserTrainingTopicRepository;
import com.wildannn.user.service.TrainingTopicService;
import com.wildannn.user.service.UserTrainingTopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserTrainingTopicServiceImpl implements UserTrainingTopicService {

    @Autowired
    private final UserTrainingTopicRepository topicsRepository;
    private final TrainingTopicService topicService;
    private final UserRepository userRepository;


    @Override
    public UserTrainingTopic enrollTopic(Integer userId, Integer topicId, Password password) {
        //Cek apakah user dengan id x ada
        if(userRepository.findById(String.valueOf(userId)).isEmpty()) {
            throw new RuntimeException(ErrorMessage.NOT_FOUND);
        }

        //Jika user ada, akan dicek apakah masih ada enroll topic yang sama
        if(topicsRepository.findByUserIdAndTrainingTopicId(userId, topicId).isPresent())
            throw new RuntimeException(ErrorMessage.HAVE_ENROLLED);

        //Jika tidak ada data duplikasi, maka akan dicek apakah password enroll telah sesuai
        TrainingTopic topic = topicService.findById(String.valueOf(topicId));
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
    public void unEnrollTopic(Integer userId, Integer topicId) {
        if(userRepository.findById(String.valueOf(userId)).isEmpty())
            throw new RuntimeException(ErrorMessage.NOT_FOUND);

        if(topicsRepository.findByUserIdAndTrainingTopicId(userId, topicId).isEmpty())
            throw new RuntimeException(ErrorMessage.NOT_ENROLLED);

        topicsRepository.deleteByUserIdAndTrainingTopicId(userId, topicId);
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

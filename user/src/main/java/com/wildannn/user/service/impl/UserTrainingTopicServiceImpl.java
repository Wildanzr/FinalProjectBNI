package com.wildannn.user.service.impl;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.handler.ErrorMessage;
import com.wildannn.user.model.Password;
import com.wildannn.user.repository.UserTrainingTopicRepository;
import com.wildannn.user.service.TrainingTopicService;
import com.wildannn.user.service.UserTrainingTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

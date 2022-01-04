package com.wildannn.user.service.impl;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.generator.IdGenerator;
import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.repository.TrainingTopicRepository;
import com.wildannn.user.service.TrainingTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingTopicServiceImpl implements TrainingTopicService {

    @Autowired
    private final TrainingTopicRepository topicRepository;
    private final IdGenerator idGenerator;
    private final KafkaProducer producer;
    
    @Override
    public TrainingTopic create(TrainingTopic topic) {
        TrainingTopic trainingTopic = this.makeTopic(topic);

        return topicRepository.save(trainingTopic);
    }

    @Override
    public TrainingTopic makeTopic(TrainingTopic topic) {
        String sequenceID = String.valueOf(idGenerator.generateId(TrainingTopic.SEQUENCE));

        return TrainingTopic.builder()
                .id(sequenceID)
                .name(topic.getName())
                .enrollCode(topic.getEnrollCode())
                .description(topic.getDescription())
                .build();
    }

    @Override
    public TrainingTopic findById(String id) {
        return topicRepository.findById(id).orElseThrow(() -> new RuntimeException(MessageResponse.NOT_FOUND));
    }

    @Override
    public List<TrainingTopic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public TrainingTopic update(String id, TrainingTopic update) {
        TrainingTopic updated = this.findById(id);

        updated.setName(update.getName());
        updated.setDescription(update.getDescription());
        updated.setEnrollCode(update.getEnrollCode());
        updated.setUpdatedAt(new Date());

        return topicRepository.save(updated);
    }

    @Override
    public void delete(String id) {
        TrainingTopic deleted = this.findById(id);
        producer.produce("Topic " +deleted.getName()+ " telah dihapus");

        topicRepository.delete(deleted);
    }

    @Override
    public TrainingTopicModel convertToModel(TrainingTopic topic) {
        return TrainingTopicModel.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
    }

    @Override
    public List<TrainingTopicModel> convertToModels(List<TrainingTopic> topics) {
        List<TrainingTopicModel> models = new ArrayList<>();

        for(TrainingTopic a : topics) {
            TrainingTopicModel model = this.convertToModel(a);
            models.add(model);
        }
        return models;
    }
}

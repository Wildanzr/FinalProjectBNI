package com.wildannn.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_training_topics")
@Data
@NoArgsConstructor
public class UserTrainingTopic {

    @Id
    private Integer userId;

    @JsonProperty("training_topic_id")
    private Integer trainingTopicId;

    @JsonProperty("training_topic_name")
    private String trainingTopicName;

    @Builder
    public UserTrainingTopic(Integer userId, TrainingTopic topic) {
        this.userId = userId;
        this.trainingTopicId = Integer.valueOf(topic.getId());
        this.trainingTopicName = topic.getName();
    }
}

package com.wildannn.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollModel {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("training_topic_id")
    private Integer trainingTopicId;

    @JsonProperty("training_topic_name")
    private String trainingTopicName;

}

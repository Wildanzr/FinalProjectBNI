package com.wildannn.user.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("training_topics")
@Data
@Builder
public class TrainingTopic {

    @Id
    private String id;

    private String name;
    private String enrollCode;
    private String description;
}

package com.wildannn.user.payload;

import com.wildannn.user.model.TrainingTopicModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TrainingTopicResponse {
    private Boolean status = true;
    private String message;
    private List<TrainingTopicModel> data;

    @Builder
    public TrainingTopicResponse(String message, List<TrainingTopicModel> data) {
        this.message = message;
        this.data = data;
    }
}

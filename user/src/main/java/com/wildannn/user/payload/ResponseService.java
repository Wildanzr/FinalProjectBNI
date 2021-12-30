package com.wildannn.user.payload;

import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {

    public UserResponse makeUserResponse(String message, UserModel model) {
        List<UserModel> modelList = new ArrayList<>();
        modelList.add(model);

        return UserResponse.builder()
                .message(message)
                .data(modelList)
                .build();
    }

    public UserResponse makeUsersResponse(String message, List<UserModel> modelList) {
        return UserResponse.builder()
                .message(message)
                .data(modelList)
                .build();
    }

    public TrainingTopicResponse makeTrainingTopicResponse(String message, TrainingTopicModel model) {
        List<TrainingTopicModel> modelList = new ArrayList<>();
        modelList.add(model);

        return TrainingTopicResponse.builder()
                .message(message)
                .data(modelList)
                .build();
    }

    public TrainingTopicResponse makeTrainingTopicsResponse(String message, List<TrainingTopicModel> modelList) {
        return TrainingTopicResponse.builder()
                .message(message)
                .data(modelList)
                .build();
    }
}

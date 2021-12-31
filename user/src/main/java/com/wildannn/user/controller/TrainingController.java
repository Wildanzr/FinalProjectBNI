package com.wildannn.user.controller;

import com.wildannn.user.entity.TrainingTopic;
import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.payload.ResponseService;
import com.wildannn.user.payload.TrainingTopicResponse;
import com.wildannn.user.service.TrainingTopicService;
import com.wildannn.user.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingTopicService topicService;
    private final ErrorMessageService errorMessageService;
    private final ResponseService responseService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findTopic(@PathVariable("id") String id) {
        try {
            TrainingTopic topic = topicService.findById(id);
            TrainingTopicModel model = topicService.convertToModel(topic);
            TrainingTopicResponse response = responseService.
                    makeTrainingTopicResponse("Success get training topic id:"+id, model);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @GetMapping
    public ResponseEntity<?> listTopic() {
        List<TrainingTopic> topics = topicService.findAll();
        List<TrainingTopicModel> modelList = topicService.convertToModels(topics);

        TrainingTopicResponse response = responseService.
                makeTrainingTopicsResponse("Success get all training topic", modelList);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createTopic(@Valid @RequestBody TrainingTopic topic) {
        try {
            TrainingTopic newTopic = topicService.create(topic);
            TrainingTopicModel model = topicService.convertToModel(newTopic);
            TrainingTopicResponse response = responseService
                    .makeTrainingTopicResponse("Success create training topic", model);

            return ResponseEntity.status(201).body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTrainingTopic(@PathVariable("id") String id, @RequestBody TrainingTopic topic) {
        try {
            TrainingTopic updated = topicService.update(id, topic);
            TrainingTopicModel model = topicService.convertToModel(updated);
            TrainingTopicResponse response = responseService
                    .makeTrainingTopicResponse("Success updated training topic id:"+id, model);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrainingTopic(@PathVariable("id") String id) {
        try {
            topicService.delete(id);
            TrainingTopicResponse response = responseService
                    .makeTrainingTopicResponse("Success deleted training topic id:"+id, null);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}
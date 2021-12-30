package com.wildannn.user.controller;

import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.model.Password;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.service.UserTrainingTopicService;
import com.wildannn.user.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/academy")
@RequiredArgsConstructor
@Log4j2
public class UserTrainingTopicController {

    private final UserTrainingTopicService topicService;
    private final ErrorMessageService errorMessageService;

    @PostMapping("/enroll/{userId}/{topicId}")
    public ResponseEntity<?> enrollTopic(@PathVariable("userId") Integer userId,
                                         @PathVariable("topicId") String topicId,
                                         @Valid @RequestBody Password password) {

        try {
            UserTrainingTopic enroll = topicService.enrollTopic(userId, topicId, password);

            return ResponseEntity.ok().build();
        }catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }

    }
}

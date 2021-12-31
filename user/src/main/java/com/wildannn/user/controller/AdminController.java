package com.wildannn.user.controller;

import com.wildannn.user.entity.User;
import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.payload.ResponseService;
import com.wildannn.user.payload.UserResponse;
import com.wildannn.user.service.UserService;
import com.wildannn.user.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ResponseService responseService;
    private final ErrorMessageService errorMessageService;

    @GetMapping("/unapproved")
    public ResponseEntity<?> getUnapprovedUsers() {
        List<User> users = userService.getUnapprovedUsers();
        List<UserModel> userList = userService.convertToModels(users);
        UserResponse response = responseService.
                makeUsersResponse(MessageResponse.ALL_UNAPPROVED_USER, userList);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/approve/{userId}")
    public ResponseEntity<?> approveUser(@PathVariable("userId") String userId) {
        try {
            User user = userService.approveUser(userId, true);
            UserModel model = userService.convertToModel(user);
            UserResponse response = responseService.
                    makeUserResponse(MessageResponse.SUCCESS_APPROVE, model);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/approve")
    public ResponseEntity<?> approveUsers(@RequestBody List<Integer> iDs) {
        if(iDs.isEmpty()) {
            Exception ex = new Exception(MessageResponse.EMPTY_IDS);
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }

        try {
            List<User> users = userService.approveUsers(iDs);
            List<UserModel> models = userService.convertToModels(users);
            UserResponse response = responseService
                    .makeUsersResponse(MessageResponse.SUCCESS_APPROVE_ALL, models);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}

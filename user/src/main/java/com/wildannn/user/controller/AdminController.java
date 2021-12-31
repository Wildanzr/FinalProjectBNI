package com.wildannn.user.controller;

import com.wildannn.user.entity.User;
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
                makeUsersResponse("Success get all unapproved user", userList);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/approve/{userId}")
    public ResponseEntity<?> approveUser(@PathVariable("userId") String userId) {
        try {
            User user = userService.approveUser(userId);
            UserModel model = userService.convertToModel(user);
            UserResponse response = responseService.
                    makeUserResponse("Success approve user id:"+userId, model);

            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}

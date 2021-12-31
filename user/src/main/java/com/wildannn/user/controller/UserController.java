package com.wildannn.user.controller;

import com.wildannn.user.entity.User;
import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.payload.ResponseService;
import com.wildannn.user.payload.UserResponse;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.service.UserService;
import com.wildannn.user.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;
    private final ErrorMessageService errorMessageService;
    private final ResponseService responseService;

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<User> users = userService.findAll();
        List<UserModel> userList = userService.convertToModels(users);
        UserResponse response = responseService.
                makeUsersResponse(MessageResponse.ALL_USER, userList);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser (@PathVariable("id") String id) {
        try {
            User user = userService.findById(id);
            UserModel model = userService.convertToModel(user);

            UserResponse response = responseService.
                    makeUserResponse(MessageResponse.USER, model);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User newUser = userService.create(user);
            UserModel model = userService.convertToModel(newUser);

            UserResponse response = responseService.
                    makeUserResponse(MessageResponse.CREATED_USER, model);

            return ResponseEntity.status(201).body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userService.update(id, user);
            UserModel model = userService.convertToModel(updatedUser);

            UserResponse response = responseService.
                    makeUserResponse(MessageResponse.UPDATED_USER, model);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            userService.delete(id);
            UserResponse response = responseService.
                    makeUserResponse(MessageResponse.DELETED_USER, null);

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}
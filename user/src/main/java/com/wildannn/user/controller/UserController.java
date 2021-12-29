package com.wildannn.user.controller;

import com.wildannn.user.entity.User;
import com.wildannn.user.entity.UserRole;
import com.wildannn.user.handler.ErrorMessage;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.model.UserRoleModel;
import com.wildannn.user.payload.UserResponse;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.service.UserRoleService;
import com.wildannn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<User> users = userService.findAll();
        List<UserModel> userList = this.usersToUserMolels(users);

        UserResponse response = UserResponse.builder()
                .message("Success get all user")
                .data(userList)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser (@PathVariable("id") String id) {
        try {
            List<UserModel> userList = new ArrayList<>();
            User user = userService.findById(id);
            UserRole role = userRoleService.findById(String.valueOf(user.getRoleTypeId()));
            UserModel userModel = this.userToUserModel(role, user);

            userList.add(userModel);
            UserResponse response = UserResponse.builder()
                    .message("Success get user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.getMessage().equals(ErrorMessage.NOT_FOUND)) {
                error = ErrorResponse.builder()
                        .message(ErrorMessage.NOT_FOUND)
                        .status(404)
                        .build();
            }

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            List<UserModel> userList = new ArrayList<>();
            log.info("Pass");
            User newUser = userService.create(user);
            log.info("Pass");
            UserRole role = userRoleService.findById(String.valueOf(newUser.getRoleTypeId()));
            log.info("Pass");
            UserModel userModel = this.userToUserModel(role, newUser);
            log.info("Pass");

            userList.add(userModel);
            log.info("Pass");
            UserResponse response = UserResponse.builder()
                    .message("Success created user")
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.getMessage().equals(ErrorMessage.NOT_FOUND)) {
                error = ErrorResponse.builder()
                        .message(ErrorMessage.NOT_FOUND)
                        .status(404)
                        .build();
            }

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
        try {
            List<UserModel> userList = new ArrayList<>();
            User updatedUser = userService.update(id, user);
            UserRole role = userRoleService.findById(String.valueOf(updatedUser.getRoleTypeId()));
            UserModel model = this.userToUserModel(role, updatedUser);

            userList.add(model);
            UserResponse response = UserResponse.builder()
                    .message("Success updated user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.getMessage().equals(ErrorMessage.NOT_FOUND)) {
                error = ErrorResponse.builder()
                        .message(ErrorMessage.NOT_FOUND)
                        .status(404)
                        .build();
            }

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            List<UserModel> userList = new ArrayList<>();
            userService.delete(id);

            UserResponse response = UserResponse.builder()
                    .message("Success deleted user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.getMessage().equals(ErrorMessage.NOT_FOUND)) {
                error = ErrorResponse.builder()
                        .message(ErrorMessage.NOT_FOUND)
                        .status(404)
                        .build();
            }

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    private UserModel userToUserModel(UserRole role, User user) {
        UserRoleModel userRoleModel = this.userRoleToUserRoleModel(role);

        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .status(user.getStatus())
                .userRoleModel(userRoleModel)
                .trainingTopic(user.getTrainingTopicId())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    private List<UserModel> usersToUserMolels(List<User> users) {
        List<UserModel> userList = new ArrayList<>();
        List<UserRole> roles = userRoleService.findAll();

        for(User a : users) {
            UserRole role = roles.get(a.getRoleTypeId()-1);
            UserModel model = this.userToUserModel(role, a);
            userList.add(model);
        }

        return userList;
    }

    private UserRoleModel userRoleToUserRoleModel(UserRole userRole) {

        return UserRoleModel.builder()
                .id(userRole.getId())
                .name(userRole.getName())
                .createdAt(userRole.getCreatedAt())
                .build();
    }
}
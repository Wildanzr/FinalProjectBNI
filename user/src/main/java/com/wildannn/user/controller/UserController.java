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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
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
            UserRole role = userRoleService.findById(String.valueOf(user.getRole_type_id()));
            UserModel userModel = this.userToUserModel(role, user);

            userList.add(userModel);
            UserResponse response = UserResponse.builder()
                    .message("Success get user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.equals(ErrorMessage.NOT_FOUND)) {
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
            User newUser = userService.create(user);
            UserRole role = userRoleService.findById(String.valueOf(newUser.getRole_type_id()));
            UserModel userModel = this.userToUserModel(role, newUser);

            userList.add(userModel);
            UserResponse response = UserResponse.builder()
                    .message("Success created user")
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.equals(ErrorMessage.NOT_FOUND)) {
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
            UserRole role = userRoleService.findById(String.valueOf(updatedUser.getRole_type_id()));
            UserModel model = this.userToUserModel(role, updatedUser);

            userList.add(model);
            UserResponse response = UserResponse.builder()
                    .message("Success updated user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse();

            if(ex.equals(ErrorMessage.NOT_FOUND)) {
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

            if(ex.equals(ErrorMessage.NOT_FOUND)) {
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

        UserModel userModel = UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .gender(user.getGender())
                .status(user.getStatus())
                .user_role(userRoleModel)
                .training_topic_id(user.getTraining_topic_id())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build();

        return userModel;
    }

    private List<UserModel> usersToUserMolels(List<User> users) {
        List<UserModel> userList = new ArrayList<>();
        List<UserRole> roles = userRoleService.findAll();

        for(User a : users) {
            UserRole role = roles.get(a.getRole_type_id()-1);
            UserModel model = this.userToUserModel(role, a);
            userList.add(model);
        }

        return userList;
    }

    private UserRoleModel userRoleToUserRoleModel(UserRole userRole) {
        UserRoleModel userRoleModel1 = UserRoleModel.builder()
                .id(userRole.getId())
                .name(userRole.getName())
                .since(userRole.getCreated_at())
                .build();

        return userRoleModel1;
    }
}
package com.wildannn.user.controller;

import com.wildannn.user.model.User;
import com.wildannn.user.payload.UserResponse;
import com.wildannn.user.payload.ErrorResponse;
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

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<User> userList = userService.findAll();
        UserResponse response = UserResponse.builder()
                .message("Success get all usersss")
                .data(userList)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser (@PathVariable("id") String id) {
        try {
            List<User> userList = new ArrayList<>();
            User user = userService.findById(id);

            userList.add(user);
            UserResponse response = UserResponse.builder()
                    .message("Success get user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(404)
                    .build();

            return ResponseEntity.status(404).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            List<User> userList = new ArrayList<>();
            User newUser = userService.create(user);

            userList.add(newUser);
            UserResponse response = UserResponse.builder()
                    .message("Success created user")
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(404)
                    .build();

            return ResponseEntity.status(404).body(error);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
        try {
            List<User> userList = new ArrayList<>();
            User updatedUser = userService.update(id, user);

            userList.add(updatedUser);
            UserResponse response = UserResponse.builder()
                    .message("Success updated user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(404)
                    .build();

            return ResponseEntity.status(404).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            List<User> userList = new ArrayList<>();
            userService.delete(id);

            UserResponse response = UserResponse.builder()
                    .message("Success deleted user id:" +id)
                    .data(userList)
                    .build();

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(404)
                    .build();

            return ResponseEntity.status(404).body(error);
        }
    }
}
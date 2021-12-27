package com.wildannn.user.controller;

import com.wildannn.user.model.User;
import com.wildannn.user.payload.InternalError;
import com.wildannn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> listUser() {
        List<User> allUsers = userService.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUser (@PathVariable("id") String id) {
        try {
            User user = userService.findById(id);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            InternalError error = new InternalError(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        User newUser = userService.create(user);
        return ResponseEntity.ok(newUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") String id, @Valid @RequestBody User user) {
        try {
            User updatedUser = userService.update(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception ex) {
            InternalError error = new InternalError(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            InternalError error = new InternalError(ex.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
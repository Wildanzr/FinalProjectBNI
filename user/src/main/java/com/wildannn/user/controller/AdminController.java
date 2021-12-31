package com.wildannn.user.controller;

import com.wildannn.user.entity.User;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.payload.ResponseService;
import com.wildannn.user.payload.UserResponse;
import com.wildannn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final ResponseService responseService;

    @GetMapping("/unapproved")
    public ResponseEntity<?> getUnapprovedUsers() {
        List<User> users = userService.getUnapprovedUsers();
        List<UserModel> userList = userService.convertToModels(users);
        UserResponse response = responseService.
                makeUsersResponse("Success get all unapproved user", userList);

        return ResponseEntity.ok(response);
    }
}

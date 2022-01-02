package com.wildannn.user.controller;

import com.wildannn.user.entity.User;
import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.model.LoginModel;
import com.wildannn.user.model.TokenModel;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.payload.ErrorResponse;
import com.wildannn.user.payload.ResponseService;
import com.wildannn.user.payload.TokenResponse;
import com.wildannn.user.payload.UserResponse;
import com.wildannn.user.service.AuthService;
import com.wildannn.user.service.UserService;
import com.wildannn.user.service.impl.ErrorMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final ResponseService responseService;
    private final ErrorMessageService errorMessageService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) {
        try {
            User regis = authService.register(user);
            UserModel model = userService.convertToModel(regis);
            UserResponse response = responseService.
                    makeUserResponse(MessageResponse.CREATED_USER, model);

            return ResponseEntity.status(201).body(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> generateToken(@Valid @RequestBody LoginModel login) {
        try {
            TokenModel token = authService.generateToken(login);
            TokenResponse response = responseService
                    .makeTokenResponse(MessageResponse.LOGIN_SUCCESS, token.getToken());

            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = errorMessageService.errorDefinition(ex);

            return ResponseEntity.status(error.getStatus()).body(error);
        }
    }
}

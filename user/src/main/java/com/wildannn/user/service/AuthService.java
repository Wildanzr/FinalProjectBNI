package com.wildannn.user.service;

import com.wildannn.user.entity.User;
import com.wildannn.user.model.LoginModel;
import com.wildannn.user.payload.TokenResponse;

public interface AuthService {
    User register(User req);
    TokenResponse generateToken(LoginModel req);
}

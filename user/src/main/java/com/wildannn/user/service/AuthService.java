package com.wildannn.user.service;

import com.wildannn.user.entity.User;
import com.wildannn.user.model.LoginModel;
import com.wildannn.user.model.TokenModel;

public interface AuthService {
    User register(User req);
    TokenModel generateToken(LoginModel req);
}
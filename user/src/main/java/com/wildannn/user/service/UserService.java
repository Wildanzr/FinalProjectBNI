package com.wildannn.user.service;

import com.wildannn.user.entity.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User makeUser(User user);
    List<User> findAll();
    User findById(String id);
    User update(String id, User user);
    User updateUserData(User updatedUser, User willUpdateUser);
    void delete(String id);
}
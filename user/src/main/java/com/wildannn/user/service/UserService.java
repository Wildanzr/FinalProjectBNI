package com.wildannn.user.service;

import com.wildannn.user.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> findAll();
    User findById(String id);
    User update(String id, User user);
    void delete(String id);
}
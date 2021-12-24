package com.wildannn.userrole.service;

import com.wildannn.userrole.model.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole create(UserRole userRole);
    List<UserRole> findAll();
    UserRole findById(String id);
    UserRole update(String id, UserRole userRole);
    void delete(String id);
}

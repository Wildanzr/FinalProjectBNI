package com.wildannn.user.service;

import com.wildannn.user.entity.UserRole;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface UserRoleService {
    UserRole create(UserRole userRole);
    List<UserRole> findAll();
    UserRole findById(String id);
    UserRole update(String id, UserRole userRole);
    void delete(String id);

    UserRole makeUserRole(UserRole userRole);
}

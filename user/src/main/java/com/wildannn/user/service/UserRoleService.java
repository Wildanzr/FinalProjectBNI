package com.wildannn.user.service;

import com.wildannn.user.entity.UserRole;
import com.wildannn.user.model.UserRoleModel;
import org.springframework.context.annotation.Bean;

import java.util.List;


public interface UserRoleService {
    UserRole create(UserRole userRole);
    List<UserRole> findAll();
    UserRole findById(String id);
    UserRole update(String id, UserRole userRole);
    void delete(String id);

    UserRole makeUserRole(UserRole userRole);
    UserRoleModel convertToModel(UserRole role);
    List<UserRoleModel> convertToModels(List<UserRole> roles);
}

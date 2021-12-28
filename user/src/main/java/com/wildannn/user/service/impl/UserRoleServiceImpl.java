package com.wildannn.user.service.impl;

import com.wildannn.user.entity.UserRole;
import com.wildannn.user.repository.UserRoleRepository;
import com.wildannn.user.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRole create(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(String id) {
        return userRoleRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Not Found!");
        });
    }

    @Override
    public UserRole update(String id, UserRole userRole) {
        UserRole updated = this.findById(id);
        updated.setName(userRole.getName());
        updated.setUpdated_at(userRole.getUpdated_at());

        return userRoleRepository.save(updated);
    }

    @Override
    public void delete(String id) {
        UserRole deleted = this.findById(id);
        userRoleRepository.delete(deleted);
    }
}

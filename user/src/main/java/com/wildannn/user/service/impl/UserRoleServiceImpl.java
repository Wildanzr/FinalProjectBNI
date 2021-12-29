package com.wildannn.user.service.impl;

import com.wildannn.user.entity.UserRole;
import com.wildannn.user.generator.IdGenerator;
import com.wildannn.user.repository.UserRoleRepository;
import com.wildannn.user.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    private final IdGenerator idGenerator;

    @Override
    public UserRole create(UserRole userRole) {
        UserRole userRole1 = this.makeUserRole(userRole);

        return userRoleRepository.save(userRole1);
    }

    @Override
    public UserRole makeUserRole(UserRole userRole) {
        String sequenceID = String.valueOf(idGenerator.generateUseRoleId(UserRole.SEQUENCE_NAME));

        UserRole newUserRole = UserRole.builder()
                .id(sequenceID)
                .name(userRole.getName())
                .created_at(userRole.getCreated_at())
                .updated_at(userRole.getUpdated_at())
                .build();

        return newUserRole;
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

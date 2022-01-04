package com.wildannn.user.service.impl;

import com.wildannn.user.entity.UserRole;
import com.wildannn.user.generator.IdGenerator;
import com.wildannn.user.handler.MessageResponse;
import com.wildannn.user.model.UserRoleModel;
import com.wildannn.user.repository.UserRoleRepository;
import com.wildannn.user.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    private final IdGenerator idGenerator;
    private final KafkaProducer producer;

    @Override
    public UserRole create(UserRole userRole) {
        UserRole userRole1 = this.makeUserRole(userRole);

        return userRoleRepository.save(userRole1);
    }

    @Override
    public UserRole makeUserRole(UserRole userRole) {
        String sequenceID = String.valueOf(idGenerator.generateId(UserRole.SEQUENCE));

        return UserRole.builder()
                .id(sequenceID)
                .name(userRole.getName())
                .build();
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(String id) {
        return userRoleRepository.findById(id).orElseThrow(() -> new RuntimeException(MessageResponse.NOT_FOUND));
    }

    @Override
    public UserRole update(String id, UserRole userRole) {
        UserRole updated = this.findById(id);
        updated.setName(userRole.getName());
        updated.setUpdatedAt(new Date());

        return userRoleRepository.save(updated);
    }

    @Override
    public void delete(String id) {
        UserRole deleted = this.findById(id);
        producer.produce("Role " +deleted.getName()+ " telah dihapus");
        userRoleRepository.delete(deleted);
    }

    @Override
    public UserRoleModel convertToModel(UserRole role) {
        return UserRoleModel.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public List<UserRoleModel> convertToModels(List<UserRole> roles) {
        List<UserRoleModel> models = new ArrayList<>();

        for(UserRole a : roles) {
            UserRoleModel model = this.convertToModel(a);
            models.add(model);
        }

        return models;
    }
}

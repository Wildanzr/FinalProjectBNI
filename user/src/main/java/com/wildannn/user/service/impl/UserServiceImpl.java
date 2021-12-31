package com.wildannn.user.service.impl;

import com.wildannn.user.entity.User;
import com.wildannn.user.entity.UserRole;
import com.wildannn.user.entity.UserTrainingTopic;
import com.wildannn.user.generator.IdGenerator;
import com.wildannn.user.handler.ErrorMessage;
import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.model.UserRoleModel;
import com.wildannn.user.repository.UserRepository;
import com.wildannn.user.service.UserRoleService;
import com.wildannn.user.service.UserService;
import com.wildannn.user.service.UserTrainingTopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;
    private final IdGenerator idGenerator;
    private final UserRoleService userRoleService;
    private final UserTrainingTopicService userTrainingTopicService;

    @Override
    public User create(User user) {
        //Mengecek apakah sudah ada user dengan alamat email yang akan didaftarkan
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException(ErrorMessage.EMAIL_REGISTERED);
         else if(userRepository.findByUsername(user.getUsername()).isPresent())
             throw new RuntimeException(ErrorMessage.USERNAME_REGISTERED);

        User newUser = this.makeUser(user);

        return userRepository.save(newUser);
    }

    //Membuat objek user
    @Override
    public User makeUser(User user) {
        String sequenceID = String.valueOf(idGenerator.generateId(User.SEQUENCE));

        return User.builder()
                .id(sequenceID)
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .roleTypeId(user.getRoleTypeId())
                .build();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException(ErrorMessage.NOT_FOUND);
        });
    }

    @Override
    public User update(String id, User user) {
        User willUpdateUser = this.makeUser(user);
        User updatedUser =this.findById(id);

        updatedUser = updateUserData(updatedUser, willUpdateUser);
        return userRepository.save(updatedUser);
    }

    //Mengupdate data user lama dengan data user baru
    @Override
    public User updateUserData(User updatedUser, User willUpdateUser) {
        updatedUser.setEmail(willUpdateUser.getEmail());
        updatedUser.setUsername(willUpdateUser.getUsername());
        updatedUser.setPassword(willUpdateUser.getPassword());
        updatedUser.setFirstName(willUpdateUser.getFirstName());
        updatedUser.setLastName(willUpdateUser.getLastName());
        updatedUser.setGender(willUpdateUser.getGender());
        updatedUser.setStatus(willUpdateUser.getStatus());
        updatedUser.setRoleTypeId(willUpdateUser.getRoleTypeId());
        updatedUser.setUpdatedAt(willUpdateUser.getUpdatedAt());

        return updatedUser;
    }

    @Override
    public void delete(String id) {
        User deletedUser = this.findById(id);
        userRepository.delete(deletedUser);
    }

    @Override
    public UserRoleModel getRoleModel(User user) {
        List<UserRole> models = userRoleService.findAll();
        UserRole role = models.get(user.getRoleTypeId()-1);

        return userRoleService.convertToModel(role);
    }

    @Override
    public List<TrainingTopicModel> getTopicsModel(User user) {
        List<UserTrainingTopic> topics = userTrainingTopicService
                .enrolledTopics(Integer.valueOf(user.getId()));

        return userTrainingTopicService.convertToTrainingTopicModels(topics);
    }

    @Override
    public UserModel convertToModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .status(user.getStatus())
                .userRoleModel(this.getRoleModel(user))
                .topics(this.getTopicsModel(user))
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    @Override
    public List<UserModel> convertToModels(List<User> users) {
        List<UserModel> models = new ArrayList<>();

        for(User a : users) {
            UserModel model = this.convertToModel(a);
            models.add(model);
        }

        return models;
    }

    @Override
    public List<User> getUnapprovedUsers() {
        return userRepository.findAllByStatus(0);
    }

    @Override
    public User approveUser(String userId, Boolean isSolo) {
        User approved = null;
        if(isSolo)
            approved = this.findById(userId);
        else
            approved = this.approvalFindById(userId);

        approved.setStatus(1);
        approved.setUpdatedAt(new Date());
        userRepository.save(approved);

        return approved;
    }

    @Override
    public List<User> approveUsers(List<Integer> userIds) {
        List<User> approved = new ArrayList<>();

        for(Integer a : userIds) {
            User user = this.approveUser(String.valueOf(a), false);
            approved.add(user);
        }

        return approved;
    }

    @Override
    public User approvalFindById(String id) {
        return userRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException(ErrorMessage.AN_ID_NOT_FOUND);
        });
    }
}
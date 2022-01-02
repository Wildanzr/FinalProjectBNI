package com.wildannn.user.service;

import com.wildannn.user.entity.User;
import com.wildannn.user.model.TrainingTopicModel;
import com.wildannn.user.model.UserModel;
import com.wildannn.user.model.UserRoleModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    //CRUD basic
    List<User> findAll();
    User findById(String id);
    User update(String id, User user);
    User updateUserData(User updatedUser, User willUpdateUser);
    void delete(String id);

    //Bussiness logic
    User makeUser(User user);
    UserRoleModel getRoleModel(User user);
    List<TrainingTopicModel> getTopicsModel(User user);
    UserModel convertToModel(User user);
    List<UserModel> convertToModels(List<User> users);
    List<User> getUnapprovedUsers();
    User approveUser(String userId, Boolean isSolo);
    List<User> approveUsers(List<Integer> userIds);
    User approvalFindById(String id);
}
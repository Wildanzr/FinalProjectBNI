package com.wildannn.user.service.impl;

import com.wildannn.user.model.User;
import com.wildannn.user.repository.UserRepository;
import com.wildannn.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        User newUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .gender(user.getGender())
                .role_type_id(user.getRole_type_id())
                .training_topic_id(user.getTraining_topic_id())
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException("Not Found");
        });
    }

    @Override
    public User update(String id, User user) {
        User updatedUser =this.findById(id);
        updatedUser.setEmail(user.getEmail());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setFirst_name(user.getFirst_name());
        updatedUser.setLast_name(user.getLast_name());
        updatedUser.setGender(user.getGender());
        updatedUser.setStatus(user.getStatus());
        updatedUser.setRole_type_id(user.getRole_type_id());
        updatedUser.setTraining_topic_id(user.getTraining_topic_id());
        updatedUser.setUpdated_at(user.getUpdated_at());
        return userRepository.save(updatedUser);
    }

    @Override
    public void delete(String id) {
        User deletedUser = this.findById(id);
        userRepository.delete(deletedUser);
    }
}
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
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public User create(User user) {
        User newUser = this.makeUser(user);

        return userRepository.save(newUser);
    }

    //Membuat objek user
    @Override
    public User makeUser(User user) {
        String sequenceID = String.valueOf(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));

        User newUser = User.builder()
                .id(sequenceID)
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .first_name(user.getFirst_name())
                .last_name(user.getLast_name())
                .gender(user.getGender())
                .role_type_id(user.getRole_type_id())
                .training_topic_id(user.getTraining_topic_id())
                .build();

        return newUser;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(()-> {
            throw new RuntimeException("User id:" +id+ " not found.");
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
        updatedUser.setFirst_name(willUpdateUser.getFirst_name());
        updatedUser.setLast_name(willUpdateUser.getLast_name());
        updatedUser.setGender(willUpdateUser.getGender());
        updatedUser.setStatus(willUpdateUser.getStatus());
        updatedUser.setRole_type_id(willUpdateUser.getRole_type_id());
        updatedUser.setTraining_topic_id(willUpdateUser.getTraining_topic_id());
        updatedUser.setUpdated_at(willUpdateUser.getUpdated_at());

        return updatedUser;
    }

    @Override
    public void delete(String id) {
        User deletedUser = this.findById(id);
        userRepository.delete(deletedUser);
    }
}
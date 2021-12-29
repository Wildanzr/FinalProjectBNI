package com.wildannn.user.service.impl;

import com.wildannn.user.entity.User;
import com.wildannn.user.generator.IdGenerator;
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
    private final IdGenerator idGenerator;

    @Override
    public User create(User user) {
        //Mengecek apakah sudah ada user dengan alamat email yang akan didaftarkan
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new RuntimeException("Alamat email telah didaftarkan");
         else if(userRepository.findByUsername(user.getUsername()).isPresent())
             throw new RuntimeException("Username telah digunakan");

        User newUser = this.makeUser(user);

        return userRepository.save(newUser);
    }

    //Membuat objek user
    @Override
    public User makeUser(User user) {
        String sequenceID = String.valueOf(idGenerator.generateUserId(User.SEQUENCE));

        return User.builder()
                .id(sequenceID)
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .roleTypeId(user.getRoleTypeId())
                .trainingTopicId(user.getTrainingTopicId())
                .build();
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
        updatedUser.setFirstName(willUpdateUser.getFirstName());
        updatedUser.setLastName(willUpdateUser.getLastName());
        updatedUser.setGender(willUpdateUser.getGender());
        updatedUser.setStatus(willUpdateUser.getStatus());
        updatedUser.setRoleTypeId(willUpdateUser.getRoleTypeId());
        updatedUser.setTrainingTopicId(willUpdateUser.getTrainingTopicId());
        updatedUser.setUpdatedAt(willUpdateUser.getUpdatedAt());

        return updatedUser;
    }

    @Override
    public void delete(String id) {
        User deletedUser = this.findById(id);
        userRepository.delete(deletedUser);
    }
}
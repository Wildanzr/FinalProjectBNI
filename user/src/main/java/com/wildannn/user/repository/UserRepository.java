package com.wildannn.user.repository;

import com.wildannn.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
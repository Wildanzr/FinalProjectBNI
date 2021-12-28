package com.wildannn.user.repository;

import com.wildannn.user.entity.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {
}

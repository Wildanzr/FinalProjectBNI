package com.wildannn.userrole.repository;

import com.wildannn.userrole.model.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends MongoRepository<UserRole, String> {
}

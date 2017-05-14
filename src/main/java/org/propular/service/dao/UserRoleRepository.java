package org.propular.service.dao;

import org.propular.dto.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRoleRepository extends MongoRepository<UserRole, String> {

}

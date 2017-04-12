package org.propular.service.dao;

import org.propular.dto.security.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUsersRepository extends MongoRepository<AppUser, String> {
	public AppUser findByUserName(String userName);
}

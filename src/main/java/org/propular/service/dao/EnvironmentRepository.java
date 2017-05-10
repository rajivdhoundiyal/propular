package org.propular.service.dao;

import org.propular.dto.Environment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnvironmentRepository extends MongoRepository<Environment, String> {
	Environment findById(String id);
}

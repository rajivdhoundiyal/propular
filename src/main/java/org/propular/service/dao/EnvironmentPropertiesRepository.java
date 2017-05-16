package org.propular.service.dao;

import java.util.Collection;

import org.propular.dto.EnvironmentProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnvironmentPropertiesRepository extends MongoRepository<EnvironmentProperties, String> {

	Collection<EnvironmentProperties> findById(String id);
	
}

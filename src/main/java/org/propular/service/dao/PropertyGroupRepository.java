package org.propular.service.dao;

import org.propular.dto.PropertyGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropertyGroupRepository extends MongoRepository<PropertyGroup, String>  {

	public PropertyGroup findByPropertyGroupId(String propertyGroupId);
	public PropertyGroup findByPropertyGroupIdAndEnvProperties_Environment(String propertyGroupId, String env);
}

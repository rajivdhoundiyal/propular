package org.propular.service.dao;

import org.propular.dto.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectQueryRepository {

	 @Autowired
	 MongoTemplate mongoTemplate;

	 
	/*public Project findByProjectIdAndPropertyGroup(String projectId, String propertyGroupName) {
	
	}*/
}

package org.propular.service.dao;

import java.util.List;

import org.propular.dto.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ProjectRepository extends MongoRepository<Project, String> {

	public List<Project> findAll();

	public Project findByProjectId(String id);
	
}

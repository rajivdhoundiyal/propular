package org.propular.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "project")
public class Project {

	@Id
	@Field("id")
	private String projectId;

	@Field("description")
	private String description;

	private List<PropertyGroup> propertyGroup;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PropertyGroup> getPropertyGroup() {
		return propertyGroup;
	}

	public void setPropertyGroup(List<PropertyGroup> propertyGroup) {
		this.propertyGroup = propertyGroup;
	}

}

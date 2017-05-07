package org.propular.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "project")
public class Project {
	
	public static final String ID = "id";

	@Id
	@Field("id")
	private String projectId;

	@Field("name")
	@NotBlank(message="Please enter valid project name.")
	@NotEmpty(message="Please enter valid project name.")
	private String name;

	@Field("description")
	private String description;

	@DBRef
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", description=" + description
				+ ", propertyGroup=" + propertyGroup + "]";
	}

}

package org.propular.dto;

import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "propertygroup")
public class PropertyGroup {

	@Id
	@Field("id")
	private String propertyGroupId;

	@Field("propertygroupname")
	private String propertyGroupName;

	@DBRef(lazy = true)
	private Collection<EnvironmentProperties> envProperties;

	public String getPropertyGroupName() {
		return propertyGroupName;
	}

	public void setPropertyGroupName(String propertyGroupName) {
		this.propertyGroupName = propertyGroupName;
	}

	public String getPropertyGroupId() {
		return propertyGroupId;
	}

	public void setPropertyGroupId(String propertyGroupId) {
		this.propertyGroupId = propertyGroupId;
	}

	public Collection<EnvironmentProperties> getEnvProperties() {
		return envProperties;
	}

	public void setEnvProperties(Collection<EnvironmentProperties> envProperties) {
		this.envProperties = envProperties;
	}
}

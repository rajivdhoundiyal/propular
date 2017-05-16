package org.propular.dto;

import java.util.List;
import java.util.Properties;

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

	@DBRef(lazy=true)
	private List<EnvironmentProperties> envProperties;

	public String getPropertyGroupName() {
		return propertyGroupName;
	}

	public void setPropertyGroupName(String propertyGroupName) {
		this.propertyGroupName = propertyGroupName;
	}


	public List<EnvironmentProperties> getEnvProperties() {
		return envProperties;
	}

	public void setEnvProperties(List<EnvironmentProperties> envProperties) {
		this.envProperties = envProperties;
	}

	public String getPropertyGroupId() {
		return propertyGroupId;
	}

	public void setPropertyGroupId(String propertyGroupId) {
		this.propertyGroupId = propertyGroupId;
	}
	
	public Properties getProps() {
		Properties props = new Properties();
		for(Property property : envProperties.get(0).getProperties()) {
			props.put(property.getKey(), property.getValue());
		}
		
		return props;
	}

}

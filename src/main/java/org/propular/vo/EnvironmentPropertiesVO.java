package org.propular.vo;

import java.util.Collection;

import org.propular.dto.Property;

public class EnvironmentPropertiesVO {

	private String id;

	private String environment;

	private Collection<Property> properties;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Collection<Property> getProperties() {
		return properties;
	}

	public void setProperties(Collection<Property> properties) {
		this.properties = properties;
	}
}

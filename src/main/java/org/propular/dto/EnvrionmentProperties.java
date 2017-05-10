package org.propular.dto;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "environmentProperties")
public class EnvrionmentProperties {

	@Id
	@Field("id")
	private String id;

	@DBRef
	private Environment environment;

	private List<Property> properties;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

}

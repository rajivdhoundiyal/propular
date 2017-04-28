package org.propular.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "property")
public class Property {

	@Id
	@Field("key")
	private String key;

	@Field("value")
	private String value;

	@Field("masked")
	private Boolean masked;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getMasked() {
		return masked;
	}

	public void setMasked(Boolean masked) {
		this.masked = masked;
	}

}

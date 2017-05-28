package org.propular.vo;

import java.util.Collection;

public class PropertyGroupVO {

	private String propertyGroupId;

	private String propertyGroupName;

	private Collection<EnvironmentPropertiesVO> envProperties;

	public String getPropertyGroupId() {
		return propertyGroupId;
	}

	public void setPropertyGroupId(String propertyGroupId) {
		this.propertyGroupId = propertyGroupId;
	}

	public String getPropertyGroupName() {
		return propertyGroupName;
	}

	public void setPropertyGroupName(String propertyGroupName) {
		this.propertyGroupName = propertyGroupName;
	}

	public Collection<EnvironmentPropertiesVO> getEnvProperties() {
		return envProperties;
	}

	public void setEnvProperties(Collection<EnvironmentPropertiesVO> envProperties) {
		this.envProperties = envProperties;
	}

}

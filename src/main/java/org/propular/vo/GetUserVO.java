package org.propular.vo;

import java.util.Arrays;
import java.util.Collection;

public class GetUserVO {

	private String userName;
	private Collection<String> roles;
	private Collection<String> scopes;
	private Collection<String> grantTypes;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	public Collection<String> getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		if (scopes != null) {
			this.scopes = Arrays.asList(scopes.split(","));
		}
	}

	public Collection<String> getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String grantTypes) {
		if (grantTypes != null) {
			this.grantTypes = Arrays.asList(grantTypes.split(","));
		}
	}

}

package org.propular.vo;

import java.util.Collection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

	public void setScopes(Collection<String> scopes) {
		this.scopes = scopes;
	}

	public Collection<String> getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(Collection<String> grantTypes) {
		this.grantTypes = grantTypes;
	}

}

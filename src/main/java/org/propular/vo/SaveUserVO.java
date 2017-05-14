package org.propular.vo;

import java.util.Collection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SaveUserVO {

	private String userName;
	private String password;
	private Collection<String> roles;
	private Collection<String> scopes;
	private Collection<String> grantTypes;

	private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return encoder.encode(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getRoles() {
		StringBuffer buff = new StringBuffer();
		for (String role : roles) {
			buff.append(role).append(",");
		}

		return buff.toString().substring(0, buff.length() - 1);
	}

	public String getScopes() {
		StringBuffer buff = new StringBuffer();
		for (String role : scopes) {
			buff.append(role).append(",");
		}

		return buff.toString().substring(0, buff.length() - 1);
	}

	public String getGrantTypes() {
		StringBuffer buff = new StringBuffer();
		for (String role : grantTypes) {
			buff.append(role).append(",");
		}

		return buff.toString().substring(0, buff.length() - 1);
	}

	public void setRoles(Collection<String> roles) {
		this.roles = roles;
	}

	public void setScopes(Collection<String> scopes) {
		this.scopes = scopes;
	}

	public void setGrantTypes(Collection<String> grantTypes) {
		this.grantTypes = grantTypes;
	}
	
}

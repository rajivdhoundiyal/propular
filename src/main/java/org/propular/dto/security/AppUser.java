package org.propular.dto.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Document(collection = "app_users")
public class AppUser {

    @Id
    @Field("user_name")
    public String userName;

    @Field("password")
    public String password;
    
    @Field("roles")
    public String roles;
    
    public Collection<GrantedAuthority> getRoles() {
    	Collection<GrantedAuthority> result = new ArrayList<>();
    	if (roles != null) {
    		String[] _roles = roles.split(",");
    		for (String role : _roles) {
				result.add(new SimpleGrantedAuthority(role));
			}
    	}
    	if (result.size() == 0) {
    		result.add(new SimpleGrantedAuthority("USER"));
    	}
    	return result;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	@Field("scopes")
	public String scopes;

	@Field("grant_types")
	public String grantTypes;

	public String getScopes() {
		/*if (scopes != null) {
			return Arrays.asList(scopes.split(","));
		}*/
		return scopes;
	}

	public String getGrantTypes() {
		/*if (grantTypes != null) {
			return Arrays.asList(grantTypes.split(","));
		}*/
		return grantTypes;
	}
	
	public Collection<String> getScopeArray() {
		if (scopes != null) {
			return Arrays.asList(scopes.split(","));
		}
		return null;
	}
	
	public Collection<String> getGrantTypeArray() {
		if (grantTypes != null) {
			return Arrays.asList(grantTypes.split(","));
		}
		return null;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}
	
}

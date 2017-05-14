package org.propular.dto.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "app_clients")
public class AppClient {

	@Id
	@Field("client_id")
	public String clientId;

	@Field("client_secret")
	public String clientSecret;

	@Field("scopes")
	public String scopes;

	@Field("grant_types")
	public String grantTypes;

	public Collection<String> getScopes() {
		if (scopes != null) {
			return Arrays.asList(scopes.split(","));
		}
		return null;
	}

	public Collection<String> getGrantTypes() {
		if (grantTypes != null) {
			return Arrays.asList(grantTypes.split(","));
		}
		return null;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

}

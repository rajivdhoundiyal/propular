package org.propular.vo;

import java.util.Collection;

public class AggregateRoleVO {

	Collection<ClientRoleVO> clientRoles;
	Collection<ClientGrantVO> clientGrants;
	Collection<UserRoleVO> userRoles;

	public Collection<ClientRoleVO> getClientRoles() {
		return clientRoles;
	}

	public void setClientRoles(Collection<ClientRoleVO> clientRoles) {
		this.clientRoles = clientRoles;
	}

	public Collection<ClientGrantVO> getClientGrants() {
		return clientGrants;
	}

	public void setClientGrants(Collection<ClientGrantVO> clientGrants) {
		this.clientGrants = clientGrants;
	}

	public Collection<UserRoleVO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Collection<UserRoleVO> userRoles) {
		this.userRoles = userRoles;
	}

}

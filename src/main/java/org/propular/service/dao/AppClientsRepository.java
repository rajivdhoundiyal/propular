package org.propular.service.dao;

import org.propular.dto.security.AppClient;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppClientsRepository extends MongoRepository<AppClient, String> {
	public AppClient findByClientId(String clientId);
}

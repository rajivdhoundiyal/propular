package org.propular.service.dao;

import org.propular.dto.ClientRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRoleRepository extends MongoRepository<ClientRole, String> {

}

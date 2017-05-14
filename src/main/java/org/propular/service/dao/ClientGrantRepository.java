package org.propular.service.dao;

import org.propular.dto.ClientGrant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientGrantRepository extends MongoRepository<ClientGrant, String> {

}

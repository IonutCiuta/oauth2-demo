package com.sci.oauth2.client.app.dao;

import model.Authorization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationRepo extends MongoRepository<Authorization, String> {
}

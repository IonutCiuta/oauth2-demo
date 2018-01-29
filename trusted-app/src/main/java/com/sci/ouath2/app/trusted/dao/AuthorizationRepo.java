package com.sci.ouath2.app.trusted.dao;

import model.Authorization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Repository
public interface AuthorizationRepo extends MongoRepository<Authorization, String>{
}

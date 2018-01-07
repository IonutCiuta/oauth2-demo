package com.sci.ouath2.app.trusted.dao;

import com.sci.ouath2.app.trusted.model.Authentication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ionutciuta24@gmail.com on 07.01.2018.
 */
@Repository
public interface AuthenticationRepo extends MongoRepository<Authentication, String> {
}

package com.sci.oauth2.dao;

import com.sci.oauth2.model.ApplicationDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Repository
public interface AppDetailsRepo extends MongoRepository<ApplicationDetails, String>{

}

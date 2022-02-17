package com.knuipalab.dsmp.domain.project;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project,String> {

    @Query("{'creator._id' : ?0}")
    List<Project> findByCreator(ObjectId creatorId);
}

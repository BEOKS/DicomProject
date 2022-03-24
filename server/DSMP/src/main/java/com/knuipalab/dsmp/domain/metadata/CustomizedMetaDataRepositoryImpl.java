package com.knuipalab.dsmp.domain.metadata;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.Assert;

public class CustomizedMetaDataRepositoryImpl implements CustomizedMetaDataRepository{

    private final MongoTemplate mongoTemplate;

    public CustomizedMetaDataRepositoryImpl(MongoTemplate mongoTemplate) {

        Assert.notNull(mongoTemplate, "MongoTemplate must not be null!");
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void updateType(String metadataId, String type) {
        Query query = new Query(Criteria.where("_id").is(metadataId));
        Update update = new Update();
        update.set("body.type", type);
        mongoTemplate.updateFirst(query,update,"metadata");
    }
}

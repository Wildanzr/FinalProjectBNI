package com.wildannn.user.generator;

import com.wildannn.user.entity.UserDbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


@Service
public class IdGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public Long generateUserId(String seqName) {
        UserDbSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                UserDbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public Long generateUseRoleId(String seqName) {
        UserDbSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                UserDbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}

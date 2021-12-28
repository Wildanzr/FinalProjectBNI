package com.wildannn.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user_db_sequence")
@Data
public class UserDbSequence {

    @Id
    private String id;

    private Long seq;
}

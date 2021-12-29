package com.wildannn.user.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("db_sequence")
@Data
public class UserRoleDbSequence {

    @Id
    private String id;

    private Long seq;
}

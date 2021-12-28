package com.wildannn.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("database_sequences")
@Data
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;
}

package com.wildannn.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    private String id;
    private String email;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer status;
    private Integer role_type_id;
    private Integer training_topic_id;
    private Date created_at;
    private Date updated_at;
}
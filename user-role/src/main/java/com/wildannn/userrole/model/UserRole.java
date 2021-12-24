package com.wildannn.userrole.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user_roles")
public class UserRole {
    private String id;
    private String name;
    private Date created_at;
    private Date updated_at;
}

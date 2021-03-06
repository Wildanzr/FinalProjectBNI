package com.wildannn.user.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@Document("user_roles")
public class UserRole {

    @Transient
    public static final String SEQUENCE = "user_roles_sequence";

    @Id
    private String id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 50, message = "Role name must be at least 3  and below 50 characters")
    private String name;

    private Date createdAt;
    private Date updatedAt;

    @Builder
    public UserRole(String id, String name) {
        this.id = id;
        this.name = name;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}

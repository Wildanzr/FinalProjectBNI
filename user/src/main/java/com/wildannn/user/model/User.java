package com.wildannn.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@Document("users")
@NoArgsConstructor
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private String id;

    @NotEmpty(message = "Email tidak boleh kosong")
    @Email(message = "Penulisan alamat email tidak valid")
    private String email;

    @NotEmpty(message = "Username tidak boleh kosong")
    @Size(min=3, max=20, message = "Username harus terdiri dari 3 hingga 20 karakter")
    private String username;

    @NotEmpty(message = "Password tidak boleh kosong")
    @Size(min=8, max=50, message = "Password harus terdiri dari 8 hingga 50 karakter")
    private String password;

    @NotEmpty(message = "First_name tidak boleh kosong")
    @Size(min=1, max=100, message = "Firstname harus terdiri dari 1 hingga 100 karakter")
    private String first_name;

    @NotEmpty(message = "Last_name tidak boleh kosong")
    @Size(min=1, max=100, message = "Lastname harus terdiri dari 1 hingga 100 karakter")
    private String last_name;
    
    @NotEmpty(message = "Gender tidak boleh kosong")
    @Size(max=1, message = "Gender harus terdiri dari 1 karakter L/P")
    private String gender;

    private Integer status;

    @NotNull(message = "Role type tidak boleh kosong")
    @Max(9)
    private Integer role_type_id;

    @NotNull(message = "Training topic tidak boleh kosong")
    @Max(9)
    private Integer training_topic_id;

    private Date created_at;
    private Date updated_at;

    @Builder
    public User(String id,String email, String username, String password, String first_name, String last_name, String gender, Integer role_type_id, Integer training_topic_id) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.role_type_id = role_type_id;
        this.training_topic_id = training_topic_id;

        //Diisi oleh sistem
        this.status = 0;
        this.created_at = new Date();
        this.updated_at = new Date();
    }
}
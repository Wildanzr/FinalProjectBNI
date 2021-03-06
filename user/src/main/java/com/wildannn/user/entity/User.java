package com.wildannn.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@Data
@Document("users")
@NoArgsConstructor
@ToString
public class User implements UserDetails {

    @Transient
    public static final String SEQUENCE = "user_db_sequence";

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
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty(message = "lastName tidak boleh kosong")
    @Size(min=1, max=100, message = "Lastname harus terdiri dari 1 hingga 100 karakter")
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty(message = "Gender tidak boleh kosong")
    @Size(max=1, message = "Gender harus terdiri dari 1 karakter L/P")
    private String gender;

    private Integer status;

    @NotNull(message = "Role type tidak boleh kosong")
    @Max(9)
    @JsonProperty("role_type_id")
    private Integer roleTypeId;

    @JsonProperty("created_at")
    private Date createdAt;

    @JsonProperty("updated_at")
    private Date updatedAt;

    @Builder
    public User(String id,String email, String username, String password, String firstName, String lastName, String gender, Integer roleTypeId) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.roleTypeId = roleTypeId;

        //Diisi oleh sistem
        this.status = 0;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
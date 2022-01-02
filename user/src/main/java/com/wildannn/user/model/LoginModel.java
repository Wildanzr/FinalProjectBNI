package com.wildannn.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class LoginModel {

    @NotEmpty(message = "Username tidak boleh kosong")
    private String username;

    @NotEmpty(message = "Password tidak boleh kosong")
    private String password;

    @Builder
    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

package com.wildannn.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Password {

    @NotEmpty(message = "Password tidak boleh kosong")
    @Size(min = 5, max = 10, message = "Password terdiri dari 5 hingga 10 karakter")
    private String password;
}

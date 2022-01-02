package com.wildannn.user.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenModel {
    private String token;

    @Builder
    public TokenModel(String token) {
        this.token = token;
    }
}
